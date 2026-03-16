package com.sprint.mission.discodeit.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
public class UserStatus {
    //필드
    private final UUID id;
    private final Instant createdAt;
    private Instant updatedAt;
    private final UUID userid;
    private Instant lastOnline;


    public UserStatus(UUID userid, Instant lastOnline) {
        this.id = UUID.randomUUID();
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
        this.userid = userid;
        this.lastOnline = lastOnline;
    }

    // 온라인인지 아닌지 (5분 이내이면 true, 아니면 false)
    public User.Status isOnline() {
        // 현재(Instant.now()) -(minusSeconds) 5분(5 * 60) 이 이후이면(isAfter)
        if (lastOnline.isAfter(Instant.now().minusSeconds(5 * 60))) {
            return User.Status.ONLINE;
        }
        return User.Status.OFFLINE;
    }

    private void update() {
        this.updatedAt = Instant.now();
    }
    public void updateLastOnline() {
        this.lastOnline = Instant.now();
        update();
    }
}
