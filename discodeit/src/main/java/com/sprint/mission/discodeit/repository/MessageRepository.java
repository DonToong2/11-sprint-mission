package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Message;

import java.util.UUID;

public interface MessageRepository {
    void insertMessage(Message message);
    boolean isExistsMessage(UUID id);
    Message findMessage(UUID id);
    void updateMessage(Message message);
    void deleteMessage(UUID id);

}
