package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.response.NotificationDto;
import com.sprint.mission.discodeit.entity.Notification;
import com.sprint.mission.discodeit.exception.notification.NotificationNotFoundException;
import com.sprint.mission.discodeit.mapper.NotificationMapper;
import com.sprint.mission.discodeit.repository.NotificationRepository;
import com.sprint.mission.discodeit.service.NotificationService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BasicNotificationService implements NotificationService {

  private final NotificationRepository notificationRepository;
  private final NotificationMapper notificationMapper;

  @Override
  @Transactional(readOnly = true)
  public List<NotificationDto> findAll(UUID receiverId) {

    // receiverId에 대한 전체 알림 목록 조회
    // Stream<Notification> → map()을 통하여 Stream<NotificationDto>로 변환
    // 이후 toList()를 통해 List<NotifiationDto>로 수집(변환)
    return notificationRepository.findAllByReceiverId(receiverId).stream()
        .map(notificationMapper::toDto)
        .toList();
  }

  @Override
  @Transactional
  public void delete(UUID notificationId, UUID userId) {

    // notificationId에 해당하는 Notification 객체를 가져옴
    Notification notification = notificationRepository.findById(notificationId).orElseThrow(
        () -> new NotificationNotFoundException(notificationId)
    );

    // Notification 객체 내의 receiverId가 userId가 아닐 경우 403 예외 반환
    if (!notification.getReceiver().getId().equals(userId)) {
      throw new AccessDeniedException("자신의 알림만 삭제할 수 있습니다.");
    }

    // 가져온 Notification 객체를 삭제시킴
    notificationRepository.delete(notification);

  }

}
