package com.sprint.mission.discodeit.event;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class WebSocketRequiredEventListener {

  private final SimpMessagingTemplate messagingTemplate;

  // MessageService.create 메서드 후 클라이언트로 메시지 전송(클라이언트 기준으로는 메시지 수신)
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleMessage(MessageCreatedEvent event) {
    messagingTemplate.convertAndSend("/sub/channels." + event.channelId() + ".messages", event);
  }
}
