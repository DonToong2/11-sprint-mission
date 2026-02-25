package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;

import java.util.UUID;

public interface MessageService {
    void createMessage(Message message);

    void readMessageAll(UUID id);

    void updateMessageContent(UUID id, String newContent);

    void deleteMessage(UUID id);
}
