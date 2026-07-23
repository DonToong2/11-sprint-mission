package com.sprint.mission.discodeit.event;

import com.sprint.mission.discodeit.dto.response.ChannelDto;
import java.time.Instant;
import lombok.Getter;

@Getter
public class ChannelUpdatedEvent extends UpdatedEvent<ChannelDto> {

  public ChannelUpdatedEvent(ChannelDto from, ChannelDto to, Instant updatedAt) {
    super(from, to, updatedAt);
  }

}
