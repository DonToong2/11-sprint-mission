package com.sprint.mission.discodeit.security.jwt;

import static com.sprint.mission.discodeit.security.jwt.JwtTokenProvider.REFRESH_TOKEN_COOKIE_NAME;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtLogoutHandler implements LogoutHandler {

  @Override
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication authentication
  ) {

    // 쿠키 생성
    Cookie cookie = new Cookie(REFRESH_TOKEN_COOKIE_NAME, null);

    // JavaScript 차단, 내 도메인 내의 모든 URI에 쿠키 적용
    cookie.setHttpOnly(true);
    cookie.setPath("/");

    // 즉시 만료되도록 유도
    cookie.setMaxAge(0);

    // 브라우저 쿠키 갱신(즉시 만료된 쿠키로 갱신되기 때문에 삭제 개념)
    response.addCookie(cookie);
  }
}
