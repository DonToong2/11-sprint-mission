package com.sprint.mission.discodeit.dto.read;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ChannelDto(
    UUID id,
    Channel.ChannelType type,
    String name,
    String description,
    List<User> participants,
    Instant lastMessageAt
) {

}
