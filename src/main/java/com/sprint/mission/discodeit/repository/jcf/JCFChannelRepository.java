package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

public class JCFChannelRepository implements ChannelRepository {

    private final Map<UUID, Channel> channels = new HashMap<>();

    @Override
    public void insert(Channel channel) {
        channels.put(channel.getId(), channel);
    }

    @Override
    public Channel findById(UUID id) {
        Channel channel = channels.get(id);
        if (channel == null) {
            throw new NoSuchElementException("해당 채널은 존재하지 않습니다. id : " + id);
        }
        return channel;
    }

    @Override
    public void update(Channel channel) {
    }

    @Override
    public void delete(UUID id) {
        channels.remove(id);
    }
}
