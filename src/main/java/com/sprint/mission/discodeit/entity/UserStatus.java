package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.entity.base.BaseUpdatableEntity;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;

@Getter
public class UserStatus extends BaseUpdatableEntity {

  // 연관관계 필드
  private final UUID userId; // User의 UUID id
  private Instant lastOnlineAt;


  public UserStatus(UUID userId, Instant lastOnlineAt) {
    this.userId = userId;
    this.lastOnlineAt = lastOnlineAt;
  }

  // 온라인인지 아닌지 (5분 이내이면 true, 아니면 false)
  public User.Status status() {
    // 현재(Instant.now()) -(minusSeconds) 5분(5 * 60) 이 이후이면(isAfter)
    if (lastOnlineAt.isAfter(Instant.now().minusSeconds(5 * 60))) {
      return User.Status.ONLINE;
    }
    return User.Status.OFFLINE;
  }

  public void updateLastOnline() {
    this.lastOnlineAt = Instant.now();
  }
}
