package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFMessageRepository;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFMessageService implements MessageService {
    //    private final Map<UUID, Message> messages = new HashMap<>();

    private final MessageRepository messageRepository;

    public JCFMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Create
    @Override
    public void createMessage(Message message) {
        // 저장 로직 분리 전
//        messages.put(message.getId(), message);
//        System.out.println("메시지를 생성하였습니다.");
//        System.out.println();

        // 저장 로직 분리 후
        messageRepository.insertMessage(message);
        System.out.println("메시지를 생성하였습니다.");
        System.out.println();
    }

    // Read
    @Override
    public void readMessageAll(UUID id) {
        // 저장 로직 분리 전
        // NPE 방지
//        if (!messages.containsKey(id)) { System.out.println("해당 메시지가 존재하지 않습니다."); }
//        else {
//            Message message = messages.get(id);
//            System.out.println("=====메시지 정보=====\n" + message);
//        }
//        System.out.println();

        // 저장 로직 분리 후
        // NPE 방지
        if (!messageRepository.isExistsMessage(id)) { System.out.println("해당 메시지가 존재하지 않습니다."); }
        else {
            Message message = messageRepository.findMessage(id);
            System.out.println("=====메시지 정보=====\n" + message);
        }
        System.out.println();
    }

    // Update
    @Override
    public void updateMessageContent(UUID id, String newContent) {
        // 저장 로직 분리 전
//        // NPE 방지
//        if (!messages.containsKey(id)) { System.out.println("해당 메시지가 존재하지 않습니다."); }
//        else {
//            Message message = messages.get(id);
//            System.out.println("수정 전 메시지 : " + message.getContent());
//            message.updateContent(newContent);
//            System.out.println("수정 후 메시지 : " + message.getContent());
//        }
//        System.out.println();

        // 저장 로직 분리 후
        // NPE 방지
        if (!messageRepository.isExistsMessage(id)) { System.out.println("해당 메시지가 존재하지 않습니다."); }
        else {
            Message message = messageRepository.findMessage(id);
            System.out.println("수정 전 메시지 : " + message.getContent());
            message.updateContent(newContent);
            System.out.println("수정 후 메시지 : " + message.getContent());
        }
        System.out.println();
    }

    // Delete
    @Override
    public void deleteMessage(UUID id) {
        // 저장 로직 분리 전
//        // NPE 방지
//        if (!messages.containsKey(id)) { System.out.println("해당 메시지가 존재하지 않습니다."); }
//        else {
//            Message message = messages.get(id);
//            System.out.println("메시지 \"" + message.getContent() + "\"이(가) 삭제되었습니다.");
//            messages.remove(id);
//        }
//        System.out.println();

        // 저장 로직 분리 후
        // NPE 방지
        if (!messageRepository.isExistsMessage(id)) { System.out.println("해당 메시지가 존재하지 않습니다."); }
        else {
            Message message = messageRepository.findMessage(id);
            System.out.println("메시지 \"" + message.getContent() + "\"이(가) 삭제되었습니다.");
            messageRepository.deleteMessage(id);
        }
        System.out.println();
    }
}
