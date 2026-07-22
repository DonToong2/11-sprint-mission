package com.sprint.mission.discodeit.event;

import java.time.Instant;

public abstract class UpdatedEvent<T> {

  private final T data;
  private final Instant updatedAt;

  protected UpdatedEvent(T data, Instant updatedAt) {
    this.data = data;
    this.updatedAt = updatedAt;
  }

}
