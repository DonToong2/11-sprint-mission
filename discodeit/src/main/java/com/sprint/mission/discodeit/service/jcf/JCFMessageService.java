package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFMessageService implements MessageService {
    private final Map<UUID, Message> messages = new HashMap<>();

    // Create
    @Override
    public void createMessage(Message message) {
        messages.put(message.getId(), message);
        System.out.println("메시지를 생성하였습니다.");
        System.out.println();
    }

    // Read
    @Override
    public void readMessageAll(UUID id) {
        Message message = messages.get(id);
        System.out.println("=====메시지 정보=====\n" + message);
        System.out.println();
    }

    // Update
    @Override
    public void updateMessageContent(UUID id, String newContent) {
        Message message = messages.get(id);
        System.out.println("수정 전 메시지 : " + message.getContent());
        message.updateContent(newContent);
        System.out.println("수정 후 메시지 : " + message.getContent());
        System.out.println();
    }

    // Delete
    @Override
    public void deleteMessage(UUID id) {
        Message message = messages.get(id);
        messages.remove(id);
        if (message == null) {
            System.out.println("해당 메시지가 존재하지 않습니다.");
        }
        else {
            System.out.println("메시지가 삭제되었습니다.");
        }
        System.out.println();
    }
}
