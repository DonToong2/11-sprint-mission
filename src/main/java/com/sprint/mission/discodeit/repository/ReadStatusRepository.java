package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.ReadStatus;

import java.util.UUID;

public interface ReadStatusRepository {
    void insert(ReadStatus readStatus);
    ReadStatus findById(UUID id);
    ReadStatus findByUserId(UUID userId);
    void update(ReadStatus readStatus);
    void delete(UUID id);
}
