package com.sprint.mission.discodeit.exception.user;

import com.sprint.mission.discodeit.exception.ErrorCode;
import java.util.Map;

public class UserEmailAlreadyExists extends UserException {

  public UserEmailAlreadyExists(String email) {
    super(ErrorCode.DUPLICATE_EMAIL, Map.of("email", email));
  }
}
