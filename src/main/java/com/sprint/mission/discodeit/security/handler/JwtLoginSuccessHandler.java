package com.sprint.mission.discodeit.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.mission.discodeit.dto.response.UserDto;
import com.sprint.mission.discodeit.security.auth.DiscodeitUserDetails;
import com.sprint.mission.discodeit.security.jwt.JwtDto;
import com.sprint.mission.discodeit.security.jwt.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtLoginSuccessHandler implements AuthenticationSuccessHandler {

  private final JwtTokenProvider jwtTokenProvider;
  private final ObjectMapper objectMapper;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication
  ) throws ServletException, IOException {

    // 로그인 사용자 정보
    DiscodeitUserDetails userDetails = (DiscodeitUserDetails) authentication.getPrincipal();

    // Access Token과 같이 JwtDTO에 담아서 Body로 반환하기 위해 사용
    UserDto userDto = userDetails.getUserDto();

    // 토큰 생성에 사용
    String userId = userDetails.getId();

    // Access Token & Refresh Token
    String accessToken = jwtTokenProvider.generateAccessToken(userId);
    String refreshToken = jwtTokenProvider.generateRefreshToken(userId);

    // 토큰을 담기 위한 쿠키 생성
    Cookie cookie = new Cookie("REFRESH_TOKEN", refreshToken);

    // JavaScript에 접근 못하게 설정
    cookie.setHttpOnly(true);

    // 모든 사이트에 쿠키 적용
    cookie.setPath("/");

    // 쿠키 유효기간을 30일로 설정(추후 JwtProperties 클래스 생성 후 변수로 적용)
    cookie.setMaxAge(60 * 60 * 24 * 30);

    // 응답에 쿠키 추가
    response.addCookie(cookie);

    // UserDto와 Access Token을 JwtDto로 묶어 JSON Body로 반환
    JwtDto jwtDto = new JwtDto(userDto, accessToken);

    // 응답 설정(상태코드, Content-Type 등)
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    // json 형태로 변환
    objectMapper.writeValue(response.getWriter(), jwtDto);
  }

}
