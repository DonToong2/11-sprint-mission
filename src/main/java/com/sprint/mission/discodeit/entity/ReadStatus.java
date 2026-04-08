package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ReadStatus extends BaseUpdatableEntity {

  // 연관관계 필드
  private final UUID userId; // User의 UUID id
  private final UUID channelId; // Channel의 UUID id

  // 필드
  private Instant lastReadAt;

//    private final UUID messageId; // 특정 메시지를 읽은 시간이 아닌 채널 별 마지막으로 읽은 시간이기 때문에 X

  // 생성자
  public ReadStatus(UUID userId, UUID channelId, Instant lastReadAt) {
    this.lastReadAt = lastReadAt;
    this.userId = userId;
    this.channelId = channelId;
  }

  // update
  public void updateLastReadAt() {
    this.lastReadAt = Instant.now();
  }
}
