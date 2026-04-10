package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.request.ReadStatusCreateRequest;
import com.sprint.mission.discodeit.dto.request.ReadStatusUpdateRequest;
import com.sprint.mission.discodeit.entity.ReadStatus;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReadStatusService {

  ReadStatus create(ReadStatusCreateRequest dto);

  Optional<ReadStatus> find(UUID id);

  List<ReadStatus> findAllByUserId(List<UUID> userId);

  ReadStatus update(UUID id, ReadStatusUpdateRequest dto);

  void delete(UUID id);
}
