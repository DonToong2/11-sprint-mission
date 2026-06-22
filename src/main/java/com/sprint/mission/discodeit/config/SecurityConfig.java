package com.sprint.mission.discodeit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf((csrf) -> csrf
            // CSRF Token Repository 구현체를 Cookie Csrf Token Repository로 설정(Default는 Http Session Csrf...)
            // js에 접근 가능하도록 HttpOnly 옵션을 false로 설정
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            // CSRF Token 요청 핸들러 구현체를 커스텀 핸들러로 설정(Default는 XORCsrfTokenRequestAttributeHandler)
            .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())
        );
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}

