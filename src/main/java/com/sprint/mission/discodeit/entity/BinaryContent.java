package com.sprint.mission.discodeit.entity;

import com.sprint.mission.discodeit.entity.base.BaseEntity;
import java.util.UUID;
import lombok.Getter;

@Getter
public class BinaryContent extends BaseEntity {

  // 필드
  private final UUID userId; // 유저의 프로필 이미지
  private final UUID messageId; // 메세지에 담긴 첨부파일
  private final byte[] bytes; // 실제 저장할 바이너리 데이터
  private final String originalName; // 데이터의 이름
  private final String contentType; // 데이터의 타입(.png 등)

  // 생성자
  private BinaryContent(UUID userId, UUID messageId, byte[] bytes, String originalName,
      String contentType) {
    this.userId = userId;
    this.messageId = messageId;
    this.bytes = bytes;
    this.originalName = originalName;
    this.contentType = contentType;
  }

  // 프로필 이미지(정적 팩토리 메서드)
  public static BinaryContent userProfileImage(UUID userId, byte[] bytes, String originalName,
      String contentType) {
    return new BinaryContent(userId, null, bytes, originalName, contentType);
  }

  // 메시지 첨부파일(정적 팩토리 메서드)
  public static BinaryContent messageAttachment(UUID messageId, byte[] bytes, String originalName,
      String contentType) {
    return new BinaryContent(null, messageId, bytes, originalName, contentType);
  }
}
