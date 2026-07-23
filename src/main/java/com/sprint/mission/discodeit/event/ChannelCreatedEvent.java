package com.sprint.mission.discodeit.event;

import com.sprint.mission.discodeit.dto.response.ChannelDto;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ChannelCreatedEvent extends CreatedEvent<ChannelDto> {

  // 비공개 채널 생성시 필요(공개 채널에서는 null)
  private final List<UUID> receiverIds;

  public ChannelCreatedEvent(ChannelDto data, Instant createdAt, List<UUID> receiverIds) {
    super(data, createdAt);
    this.receiverIds = receiverIds;
  }

}
