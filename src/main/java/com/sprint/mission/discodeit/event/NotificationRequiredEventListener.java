package com.sprint.mission.discodeit.event;

import com.sprint.mission.discodeit.entity.Notification;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.exception.user.UserNotFoundException;
import com.sprint.mission.discodeit.repository.NotificationRepository;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class NotificationRequiredEventListener {

  private final ReadStatusRepository readStatusRepository;
  private final NotificationRepository notificationRepository;
  private final UserRepository userRepository;

  // phase 생략 시 default는 AFTER_COMMIT
  @TransactionalEventListener
  public void on(MessageCreatedEvent event) {

    // 알림이 활성화된 ChannelId로 ReadStatus를 조회
    List<ReadStatus> readStatuses =
        readStatusRepository.findByChannelIdAndNotificationEnabledTrue(event.channelId());

    for (ReadStatus readStatus : readStatuses) {

      // 메시지 작성자는 제외
      if (readStatus.getUser().getId().equals(event.userId())) {
        continue;
      }

      // 알림 생성
      Notification notification = new Notification(
          readStatus.getUser(),
          "보낸사람 #(" + event.channelName() + ")",
          event.content()
      );

      // DB 저장
      notificationRepository.save(notification);
    }
  }

  @TransactionalEventListener
  public void on(RoleUpdatedEvent event) {

    User user = userRepository.findById(event.userId()).orElseThrow(
        () -> new UserNotFoundException(event.userId())
    );

    Notification notification = new Notification(
        user,
        "권한이 변경되었습니다.",
        event.beforeRole() + "->" + event.newRole()
    );

    // DB 저장
    notificationRepository.save(notification);
  }

}
