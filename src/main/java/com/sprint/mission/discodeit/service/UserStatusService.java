package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.UserStatusCreateRequest;
import com.sprint.mission.discodeit.dto.UserStatusUpdateRequest;
import com.sprint.mission.discodeit.entity.UserStatus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserStatusService {

  UserStatus create(UserStatusCreateRequest dto);

  Optional<UserStatus> find(UUID id);

  List<UserStatus> findAll();

  UserStatus update(UUID id, UserStatusUpdateRequest dto);

  UserStatus updateByUserId(UUID userId, UserStatusUpdateRequest dto);

  void delete(UUID id);
}
