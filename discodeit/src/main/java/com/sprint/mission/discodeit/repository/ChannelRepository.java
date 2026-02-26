package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.UUID;

public interface ChannelRepository {
    void InsertChannel(Channel channel);
    boolean isExistsChannel(UUID id);
    Channel findChannel(UUID id);
    void deleteChannel(UUID id);
}
