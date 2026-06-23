package com.sprint.mission.discodeit.config;

import com.sprint.mission.discodeit.security.LoginFailureHandler;
import com.sprint.mission.discodeit.security.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final LoginSuccessHandler loginSuccessHandler;
  private final LoginFailureHandler loginFailureHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf((csrf) -> csrf
            // CSRF Token Repository 구현체를 Cookie Csrf Token Repository로 설정(Default는 Http Session Csrf...)
            // js에 접근 가능하도록 HttpOnly 옵션을 false로 설정
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            // CSRF Token 요청 핸들러 구현체를 커스텀 핸들러로 설정(Default는 XORCsrfTokenRequestAttributeHandler)
            .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())
        )
        .formLogin(login -> login
            // 기본 로그인 페이지 설정
            .loginProcessingUrl("/api/auth/login")
            // 로그인 성공 시 loginSuccessHandler 호출
            .successHandler(loginSuccessHandler)
            // 로그인 실패 시 loginFailureHandler 호출
            .failureHandler(loginFailureHandler)
        )
        .logout(logout -> logout
            // 로그아웃 처리 URL 지정
            .logoutUrl("/api/auth/logout")
        );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}

