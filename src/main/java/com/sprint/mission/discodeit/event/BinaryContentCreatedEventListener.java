package com.sprint.mission.discodeit.event;

import com.sprint.mission.discodeit.storage.BinaryContentStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class BinaryContentCreatedEventListener {

  private final BinaryContentStorage binaryContentStorage;

  // 커밋 이후에 put 메서드가 실행되도록 설정
  // phase 생략 시 기본값 TransactionPhase.AFTER_COMMIT
  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handle(BinaryContentCreatedEvent event) {
    binaryContentStorage.put(event.binaryContentId(), event.bytes());
  }

}
