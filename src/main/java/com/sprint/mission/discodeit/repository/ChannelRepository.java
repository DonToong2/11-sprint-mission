package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.UUID;

public interface ChannelRepository {
    void insert(Channel channel);

    Channel findById(UUID id);

    void update(Channel channel);

    void delete(UUID id);
}
