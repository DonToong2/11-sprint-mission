package com.sprint.mission.discodeit.config;

import com.sprint.mission.discodeit.security.LoginFailureHandler;
import com.sprint.mission.discodeit.security.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
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
            // 로그아웃 시 HttpStatusReturningLogoutSuccessHandler 호출(204 반환)
            .logoutSuccessHandler(
                new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT))
        )
        .authorizeHttpRequests(auth -> auth
                // SPA, 정적 리소스
                .requestMatchers("/", "/index.html", "/facicon.ico", "/error").permitAll()
                .requestMatchers("/assets/**").permitAll()
                // 현재 로그인 유저가 있는지 확인(없으면 401에러 반환)
                .requestMatchers(HttpMethod.GET, "/api/auth/me").permitAll()
                // Csrf Token 발급
                .requestMatchers(HttpMethod.GET, "/api/auth/csrf-token").permitAll()
                // 회원가입
                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                // 로그인
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                // 로그아웃
                .requestMatchers(HttpMethod.POST, "/api/auth/logout").permitAll()
                // Swagger
                .requestMatchers("swagger-ui.html", "swagger-ui/**", "v3/api-docs/**").permitAll()
                // Actuator
                .requestMatchers("/actuator/**").permitAll()
//              // 그 외의 모든 요청은 인증된 사용자만 가능
                .anyRequest().authenticated()
        );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}

