package com.sprint.mission.discodeit.entity;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class ReadStatus {
    // 필드
    private final UUID id;
    private final Instant createdAt;
    private Instant updatedAt;
    private final UUID userId;
    private final UUID channelId;
//    private final UUID messageId; // 특정 메시지를 읽은 시간이 아닌 채널 별 마지막으로 읽은 시간이기 때문에 X
    private Instant lastReadAt;

    // 생성자
    public ReadStatus(UUID userId, UUID channelId, Instant lastReadAt) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
        this.userId = userId;
        this.channelId = channelId;
        this.lastReadAt = lastReadAt;
    }

    // update
    private void update() {
        this.updatedAt = Instant.now();
    }
    public void updateLastReadAt() {
        this.lastReadAt = Instant.now();
        update();
    }
}
