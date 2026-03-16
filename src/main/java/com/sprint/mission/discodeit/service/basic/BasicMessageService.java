package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasicMessageService implements MessageService {
    private final MessageRepository messageRepository;

    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    // Create
    @Override
    public Message create(String content, UUID channelId, UUID userId) {
        Channel channel = channelRepository.findById(channelId);
        User author = userRepository.findById(userId);
        Message message = Message.create(content, channel, author);
        messageRepository.insert(message);
        System.out.println("메시지를 생성하였습니다.");
        System.out.println();

        return message;
    }

    // Read
    @Override
    public Message readAll(UUID id) {
        Message message = messageRepository.findById(id);
        System.out.println("=====메시지 정보=====\n" + message);
        System.out.println();

        return message;
    }

    // Update
    @Override
    public Message updateContent(UUID id, String newContent) {
        Message message = messageRepository.findById(id);
        System.out.println("수정 전 메시지 : " + message.getContent());
        message.updateContent(newContent);
        System.out.println("수정 후 메시지 : " + message.getContent());
        messageRepository.update(message);
        System.out.println();

        return message;
    }

    // Delete
    @Override
    public void delete(UUID id) {
        Message message = messageRepository.findById(id);
        System.out.println("메시지 \"" + message.getContent() + "\"이(가) 삭제되었습니다.");
        messageRepository.delete(id);
        System.out.println();
    }
}
