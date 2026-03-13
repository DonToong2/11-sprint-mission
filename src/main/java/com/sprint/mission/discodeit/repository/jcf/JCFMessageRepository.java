package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

public class JCFMessageRepository implements MessageRepository {

    private final Map<UUID, Message> messages = new HashMap<>();

    @Override
    public void insert(Message message) {
        messages.put(message.getId(), message);
    }

    @Override
    public Message findById(UUID id) {
        Message message = messages.get(id);
        if (message == null) {
            throw new NoSuchElementException("해당 메시지가 존재하지 않습니다. id : " + id);
        }
        return messages.get(id);
    }

    @Override
    public void update(Message message) {
    }

    @Override
    public void delete(UUID id) {
        messages.remove(id);
    }
}
