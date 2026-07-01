package com.sprint.mission.discodeit.security.jwt;

import com.sprint.mission.discodeit.exception.ErrorCode;
import com.sprint.mission.discodeit.exception.auth.AuthException;
import java.util.Map;

public class RefreshTokenInvalidException extends AuthException {

  public RefreshTokenInvalidException() {
    super(ErrorCode.REFRESH_TOKEN_INVALID, Map.of());
  }
}
