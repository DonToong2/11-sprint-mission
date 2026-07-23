package com.sprint.mission.discodeit.event;

import com.sprint.mission.discodeit.dto.response.BinaryContentDto;
import com.sprint.mission.discodeit.dto.response.NotificationDto;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.sse.service.SseService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class SseRequiredEventListener {

  private final SseService sseService;
  private final ReadStatusRepository readStatusRepository;

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void on(NotificationCreatedEvent event) {

    NotificationDto dto = event.getData();

    sseService.send(
        List.of(dto.receiverId()),
        "notifications.created",
        dto
    );
  }

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void on(BinaryContentUpdatedEvent event) {

    BinaryContentDto dto = event.getTo();

    // receiverId가 존재할 때 → UserService의 create, update 메서드의 프로필 이미지
    if (event.getReceiverId() != null) {
      sseService.send(
          List.of(event.getReceiverId()),
          "binaryContents.updated",
          dto
      );
    }
    // channelId가 존재할 때 → MessageService의 create 메서드의 첨부파일
    else if (event.getChannelId() != null) {
      List<UUID> receiverIds = readStatusRepository.findByChannelId(event.getChannelId()).stream()
          .map(readStatus -> readStatus.getUser().getId())
          .toList();

      sseService.send(
          receiverIds,
          "binaryContents.updated",
          dto
      );
    }
  }

}
