package com.sprint.mission.discodeit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.mission.discodeit.dto.response.ErrorResponse;
import com.sprint.mission.discodeit.exception.ErrorCode;
import com.sprint.mission.discodeit.security.LoginFailureHandler;
import com.sprint.mission.discodeit.security.LoginSuccessHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final LoginSuccessHandler loginSuccessHandler;
  private final LoginFailureHandler loginFailureHandler;
  private final ObjectMapper objectMapper;

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
            // Spring Security 기본 로그인 폼 비활성화("/login")를 위해 로그인 페이지 설정
            .loginPage("/")
            // 로그인 요청을 처리하는 URL 지정
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
        )
        .exceptionHandling(ex -> ex
            // 인증 안됨 → 401
            .authenticationEntryPoint((request, response, authenticationException) -> {
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
              response.setContentType("application/json");
              response.setCharacterEncoding("UTF-8");

              ErrorResponse errorResponse = ErrorResponse.of(
                  ErrorCode.UNAUTHORIZED,
                  HttpServletResponse.SC_UNAUTHORIZED,
                  authenticationException);

              objectMapper.writeValue(response.getWriter(), errorResponse);
            })
            // 권한 없음 → 403
            .accessDeniedHandler((request, response, accessDeniedException) -> {
              response.setStatus(HttpServletResponse.SC_FORBIDDEN);
              response.setContentType("application/json");
              response.setCharacterEncoding("UTF-8");

              ErrorResponse errorResponse = ErrorResponse.of(
                  ErrorCode.FORBIDDEN,
                  HttpServletResponse.SC_FORBIDDEN,
                  accessDeniedException);

              objectMapper.writeValue(response.getWriter(), errorResponse);
            })
        );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}

