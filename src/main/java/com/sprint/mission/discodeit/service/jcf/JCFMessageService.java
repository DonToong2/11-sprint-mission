package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFMessageRepository;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFMessageService implements MessageService {
    //    private final Map<UUID, Message> messages = new HashMap<>();

    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    public JCFMessageService(MessageRepository messageRepository,
                             ChannelRepository channelRepository,
                             UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }

    // Create
    @Override
    public Message create(String content, UUID channelId, UUID userId) {
        // 저장 로직 분리 전
//        messages.put(message.getId(), message);
//        System.out.println("메시지를 생성하였습니다.");
//        System.out.println();

        // 저장 로직 분리 후
        Channel channel = channelRepository.findChannel(channelId);
        User author = userRepository.findUser(userId);
        Message message = new Message(content, channel, author);
        messageRepository.insertMessage(message);
        System.out.println("메시지를 생성하였습니다.");
        System.out.println();

        return message;
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
