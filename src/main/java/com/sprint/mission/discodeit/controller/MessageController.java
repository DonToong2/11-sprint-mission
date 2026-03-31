package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.constant.EndPoints;
import com.sprint.mission.discodeit.dto.MessageCreateRequest;
import com.sprint.mission.discodeit.dto.MessageUpdateRequest;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(EndPoints.MESSAGE)
@Tag(name = "Message", description = "Message API")
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  // 메시지 보내기(생성)
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Message> create(
      @RequestPart("messageCreateRequest") MessageCreateRequest dto,
      @RequestPart(value = "attachments", required = false) List<MultipartFile> attachments) {
    return ResponseEntity.status(HttpStatus.CREATED).body(messageService.create(dto, attachments));
  }

  // 메시지 수정
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/{messageId}", method = RequestMethod.PATCH)
  public ResponseEntity<Message> update(@PathVariable("messageId") UUID id,
      @RequestBody MessageUpdateRequest dto) {
    return ResponseEntity.ok(messageService.update(id, dto));
  }

  // 메시지 삭제
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = "/{messageId}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable("messageId") UUID id) {
    messageService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // 특정 채널의 모든 메시지를 조회
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<Message>> readAllByChannelId(
      @RequestParam("channelId") UUID channelId) {
    return ResponseEntity.ok(messageService.findAllByChannelId(channelId));
  }
}