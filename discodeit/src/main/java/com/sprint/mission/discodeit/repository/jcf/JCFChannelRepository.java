package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFChannelRepository implements ChannelRepository {

    private final Map<UUID, Channel> channels = new HashMap<>();

    @Override
    public void insertChannel(Channel channel) {
        channels.put(channel.getId(), channel);
    }

    @Override
    public boolean isExistsChannel(UUID id) {
        return channels.containsKey(id);
    }

    @Override
    public Channel findChannel(UUID id) {
        return channels.get(id);
    }

    @Override
    public void updateChannel(Channel channel) { }

    @Override
    public void deleteChannel(UUID id) {
        channels.remove(id);
    }
}
