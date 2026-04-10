package com.sprint.mission.discodeit.mapper;

import com.sprint.mission.discodeit.dto.read.UserStatusDto;
import com.sprint.mission.discodeit.entity.UserStatus;
import org.springframework.stereotype.Component;

@Component
public class UserStatusMapper {

  public UserStatusDto toDto(UserStatus userStatus) {
    return new UserStatusDto(
        userStatus.getId(), // id
        userStatus.getUser().getId(), // userId
        userStatus.getLastActiveAt() // lastActiveAt
    );
  }

}
