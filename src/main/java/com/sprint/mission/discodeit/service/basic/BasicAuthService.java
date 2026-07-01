package com.sprint.mission.discodeit.service.basic;

import static com.sprint.mission.discodeit.security.jwt.JwtTokenProvider.REFRESH_TOKEN_COOKIE_NAME;

import com.sprint.mission.discodeit.dto.request.UserRoleUpdateRequest;
import com.sprint.mission.discodeit.dto.response.UserDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.exception.user.UserNotFoundException;
import com.sprint.mission.discodeit.mapper.UserMapper;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.security.auth.DiscodeitUserDetails;
import com.sprint.mission.discodeit.security.auth.DiscodeitUserDetailsService;
import com.sprint.mission.discodeit.security.jwt.JwtDto;
import com.sprint.mission.discodeit.security.jwt.JwtTokenProvider;
import com.sprint.mission.discodeit.security.jwt.RefreshTokenInvalidException;
import com.sprint.mission.discodeit.security.properties.JwtProperties;
import com.sprint.mission.discodeit.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BasicAuthService implements AuthService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  private final SessionRegistry sessionRegistry;

  private final JwtTokenProvider jwtTokenProvider;
  private final DiscodeitUserDetailsService userDetailsService;
  private final JwtProperties jwtProperties;

  // Role Update
  @Override
  @Transactional
  @PreAuthorize("hasRole('ADMIN')")
  public UserDto updateRole(UserRoleUpdateRequest dto) {
    log.debug("[USER_ROLE_UPTATE_START] 유저 권한 수정 시작 - 권한 수정할 유저 ID={}", dto.userId());

    User user = userRepository.findById(dto.userId()).orElseThrow(
        () -> new UserNotFoundException(dto.userId())
    );

    user.updateRole(dto.newRole());

    // 현재 로그인 중인 모든 사용자를 조회
    sessionRegistry.getAllPrincipals()
        .stream()
        // DiscodeitUserDetails 타입만 남기고
        // 특정 userId를 가진 유저를 조회 → 여기서는 권한 수정할 유저를 조회
        // 로그인 중인 사용자 id가 a, b, c, d면 a==dto.userId(), ..., d==dto.userId()
        .filter(principal ->
            principal instanceof DiscodeitUserDetails userDetails &&
                userDetails.getUserDto().id().equals(dto.userId()))
        // filter 조건에 맞는 사용자의 모든 세션을 찾아 인증 무효화(세션 만료X)
        .forEach(principal -> {
          sessionRegistry.getAllSessions(principal, false)
              .forEach(SessionInformation::expireNow);
        });

    log.info("[USER_ROLE_UPDATE_SUCCESS] 유저 권한 수정 완료 - 권한 수정한 유저 ID={}", dto.userId());

    return userMapper.toDto(user);
  }

  @Override
  public JwtDto refresh(String refreshToken, HttpServletResponse response) {

    // Refresh Token 검증
    if (refreshToken == null || !jwtTokenProvider.validateToken(refreshToken)) {
      throw new RefreshTokenInvalidException("유효하지 않은 Refresh Token입니다.");
    }

    // 토큰에서 사용자 식별 정보(subject)를 추출 후 UUID로 변환
    UUID userId = UUID.fromString(jwtTokenProvider.getSubject(refreshToken));

    // UserDetails 조회
    DiscodeitUserDetails userDetails =
        (DiscodeitUserDetails) userDetailsService.loadUserById(userId);

    // JwtDto에 담기 위해 UserDto로 추출
    UserDto userDto = userDetails.getUserDto();

    // Refresh Token으로 Access Token 재발급
    String accessToken = jwtTokenProvider.reIssueAccessToken(refreshToken);

    // Refresh Token 회전
    String newRefreshToken = jwtTokenProvider.generateRefreshToken(userId.toString());

    // Refresh Token 쿠키 교체
    Cookie cookie = new Cookie(REFRESH_TOKEN_COOKIE_NAME, newRefreshToken);

    // JavaScript 접근 차단
    cookie.setHttpOnly(true);

    // 모든 사이트에 쿠키 적용
    cookie.setPath("/");

    // 쿠키 유효기간을 30일로 설정(setMaxAge()는 초 단위이기 때문에 60을 곱하여 초 단위로 변환)
    cookie.setMaxAge(jwtProperties.getRefreshTokenExpiration() * 60);

    // 응답에 쿠키 추가
    response.addCookie(cookie);

    return new JwtDto(userDto, accessToken);
  }
}
