package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.read.MessageDto;
import com.sprint.mission.discodeit.dto.request.MessageCreateRequest;
import com.sprint.mission.discodeit.dto.request.MessageUpdateRequest;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.mapper.MessageMapper;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.MessageService;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BasicMessageService implements MessageService {

  private final MessageRepository messageRepository;
  private final ChannelRepository channelRepository;
  private final UserRepository userRepository;
  private final BinaryContentRepository binaryContentRepository;
  private final MessageMapper messageMapper;

  // Create
  @Override
  @Transactional
  public Message create(MessageCreateRequest dto, List<MultipartFile> attachments) {
    Channel channel = channelRepository.findById(dto.channelId()).orElseThrow(
        () -> new NoSuchElementException("존재하지 않는 채널입니다. Id : " + dto.channelId())
    );

    User author = userRepository.findById(dto.authorId()).orElseThrow(
        () -> new NoSuchElementException("존재하지 않는 유저입니다. Id : " + dto.authorId())
    );

    Message message = Message.create(dto.content(), channel, author);

    // 첨부파일 등록(선택)
    if (attachments != null && !attachments.isEmpty()) {
      attachments.forEach(file -> {
        try {
          BinaryContent binaryContent = BinaryContent.of(
              file.getOriginalFilename(), file.getSize(),
              file.getContentType()
          );
          binaryContentRepository.save(binaryContent);
          message.addAttachment(binaryContent); // message.getAttachments().add(binaryContent) 캡슐화
        } catch (IOException e) {
          throw new RuntimeException("첨부파일 처리 실패 : ", e);
        }
      });
    }

    messageRepository.save(message);

    return message;
  }

  // Read
  @Override
  @Transactional(readOnly = true)
  public List<MessageDto> findAllByChannelId(UUID channelId) {
    return messageRepository.findAllByChannelId(channelId).stream()
        .map(messageMapper::toDto).toList();
  }

  // Update
  @Override
  @Transactional
  public Message update(UUID id, MessageUpdateRequest dto) {
    Message message = messageRepository.findById(id).orElseThrow(
        () -> new NoSuchElementException("해당 메시지가 존재하지 않습니다. Id : " + id)
    );
    message.updateContent(dto.newContent());
    messageRepository.save(message);
    System.out.println();

    return message;
  }

  // Delete
  // 기존 메시지만 삭제
  // 고도화 후 첨부파일 삭제 추가
  @Override
  @Transactional
  public void delete(UUID id) {
    Message message = messageRepository.findById(id).orElseThrow(
        () -> new NoSuchElementException("해당 메시지가 존재하지 않습니다. Id : " + id)
    );
    // 특정 메시지에 존재하는 첨부파일 삭제
    binaryContentRepository.deleteAll(message.getAttachments());

    // 메시지 삭제
    messageRepository.deleteById(id);
  }
}
