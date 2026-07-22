package com.sprint.mission.discodeit.event;

import com.sprint.mission.discodeit.sse.service.SseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class SseNotificationEventListener {

  private final SseService sseService;

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(NotificationCreatedEvent event) {
    sseService.send(
        List.of(event.receiverId()),
        "notifications.created",
        event.notificationDto()
    );
  }

}
