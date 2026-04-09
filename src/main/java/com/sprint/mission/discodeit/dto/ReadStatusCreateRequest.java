package com.sprint.mission.discodeit.dto;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;

public record ReadStatusCreateRequest(
    User user,
    Channel channel
) {

}
