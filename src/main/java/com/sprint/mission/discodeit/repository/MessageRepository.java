package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Message;

import java.util.UUID;

public interface MessageRepository {
    void insert(Message message);

    Message findById(UUID id);

    void update(Message message);

    void delete(UUID id);

}
