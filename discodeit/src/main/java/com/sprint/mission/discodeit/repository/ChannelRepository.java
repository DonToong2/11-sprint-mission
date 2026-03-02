package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.UUID;

public interface ChannelRepository {
    void insertChannel(Channel channel);
    boolean isExistsChannel(UUID id);
    Channel findChannel(UUID id);
    void updateChannel(Channel channel);
    void deleteChannel(UUID id);
}
