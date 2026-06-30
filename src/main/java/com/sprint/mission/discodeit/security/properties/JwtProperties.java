package com.sprint.mission.discodeit.security.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "jwt") // application.yaml에서 jwt: ..에 대한 값들을 가져옴
public class JwtProperties {

  private String secretKey;
  private int accessTokenExpiration;
  private int refreshTokenExpiration;
}
