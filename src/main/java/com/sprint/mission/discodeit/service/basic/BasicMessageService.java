package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.MessageService;

import java.util.UUID;

public class BasicMessageService implements MessageService {
    private final MessageRepository messageRepository;

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    public BasicMessageService(MessageRepository messageRepository,
                               ChannelRepository channelRepository,
                               UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }

    // Create
    @Override
    public Message create(String content, UUID channelId, UUID userId) {
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
        // NPE 방지
        if (!messageRepository.isExistsMessage(id)) { System.out.println("해당 메시지가 존재하지 않습니다."); }
        else {
            Message message = messageRepository.findMessage(id);
            System.out.println("수정 전 메시지 : " + message.getContent());
            message.updateContent(newContent);
            System.out.println("수정 후 메시지 : " + message.getContent());
            messageRepository.updateMessage(message);
        }
        System.out.println();
    }

    // Delete
    @Override
    public void deleteMessage(UUID id) {
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
