package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Message;

import java.util.UUID;

public interface MessageService {
    // 코드 탬플릿에 맞게 create 메서드 수정
    Message create(String content, UUID channelId, UUID userId);
//    void createMessage(Message message);

    Message readAll(UUID id);

    Message updateContent(UUID id, String newContent);

    void delete(UUID id);
}
