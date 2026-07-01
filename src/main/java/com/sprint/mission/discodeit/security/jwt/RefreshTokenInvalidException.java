package com.sprint.mission.discodeit.security.jwt;

public class RefreshTokenInvalidException extends RuntimeException {

  public RefreshTokenInvalidException(String message) {
    super(message);
  }
}
