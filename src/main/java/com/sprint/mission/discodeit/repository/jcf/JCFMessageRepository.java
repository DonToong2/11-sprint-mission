package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<Message> findAllByChannelId(UUID channelId) {
        return messages.values().stream()
                .filter(message -> message.getChannelId().equals(channelId))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Message message) {
        messages.put(message.getId(), message);
    }

    @Override
    public void delete(UUID id) {
        messages.remove(id);
    }

    @Override
    public void deleteAllByChannelId(UUID channelId) {
        messages.values().removeIf(message -> message.getChannelId().equals(channelId));
    }
}
