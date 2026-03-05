package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFMessageRepository implements MessageRepository {

    private final Map<UUID, Message> messages = new HashMap<>();

    @Override
    public void insertMessage(Message message) { messages.put(message.getId(), message); }
    @Override
    public boolean isExistsMessage(UUID id) { return messages.containsKey(id); }
    @Override
    public Message findMessage(UUID id) { return messages.get(id); }
    @Override
    public void updateMessage(Message message) { }
    @Override
    public void deleteMessage(UUID id) { messages.remove(id); }
}
