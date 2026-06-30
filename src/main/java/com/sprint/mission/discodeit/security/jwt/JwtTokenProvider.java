package com.sprint.mission.discodeit.security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  // 비밀키
  @Value("${jwt.secret}")
  private String secretKey;

  // Access Token 유효 시간(30분으로 설정)
  @Value("${jwt.access-token-expiration}")
  private int accessTokenExpirationMinutes;

  // Refresh Token 유효 시간(30일로 설정)
  @Value("${jwt.refresh-token-expiration}")
  private int refreshTokenExpirationMinutes;

  // Access Token 발급
  public String generateAccessToken(String userId) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", userId);

    return generateToken(claims, userId, getTokenExpiration(accessTokenExpirationMinutes));
  }

  // Refresh Token 발급
  public String generateRefreshToken(String userId) {
    Map<String, Object> claims = new HashMap<>();

    return generateToken(claims, userId, getTokenExpiration(refreshTokenExpirationMinutes));

  }

  // Refresh Token으로 Access Token 갱신(재 발급)
  public String reIssueAccessToken(String refreshToken) {
    Map<String, Object> claims = getClaims(refreshToken);

    String userId = claims.get("sub").toString();

    return generateAccessToken(userId);
  }

  // 유효성 검사
  public boolean validateToken(String token) {
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);

      if (!signedJWT.verify(createVerifier())) {
        return false;
      }

      Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();

      // 만료 시간(expiration)이 지금(new Date())보다 뒤인지 체크
      return expiration.after(new Date());

    } catch (Exception e) {
      return false;
    }
  }

  private String generateToken(
      Map<String, Object> claims,
      String subject,
      Date expiration
  ) {
    try {
      JWSSigner signer = createSigner();

      JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder()
          .subject(subject)
          .issueTime(Calendar.getInstance().getTime())
          .expirationTime(expiration);

      claims.forEach(builder::claim);

      SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), builder.build());

      signedJWT.sign(signer);

      return signedJWT.serialize();
    } catch (JOSEException e) {
      throw new JwtSignatureException("Token 생성에 실패하였습니다.", e);
    }
  }

  // claims 추출
  // 만료 검증을 명시적으로 수행
  public Map<String, Object> getClaims(String token) {
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);

      if (!signedJWT.verify(createVerifier())) {
        throw new JwtSignatureException("JWT 서명 검증에 실패하였습니다.");
      }

      Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();

      if (expiration.before(new Date())) {
        throw new JwtExpiredException("JWT가 만료되었습니다.");
      }

      return signedJWT.getJWTClaimsSet().getClaims();
    } catch (ParseException e) {
      throw new JwtSignatureException("JWT 형식이 올바르지 않습니다.", e);
    } catch (JOSEException e) {
      throw new JwtSignatureException("JWT 검증 중 오류가 발생했습니다.", e);
    }
  }

  // 유효기간 생성(분 단위)
  public Date getTokenExpiration(int expirationMinutes) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, expirationMinutes);
    return calendar.getTime();
  }

  // Signer
  private JWSSigner createSigner() throws JOSEException {
    byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

    return new MACSigner(keyBytes);
  }

  // Verifier
  private JWSVerifier createVerifier() throws JOSEException {
    byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);

    return new MACVerifier(keyBytes);
  }
}
