package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.constant.EndPoints;
import com.sprint.mission.discodeit.controller.api.MessageApi;
import com.sprint.mission.discodeit.dto.request.MessageCreateRequest;
import com.sprint.mission.discodeit.dto.request.MessageUpdateRequest;
import com.sprint.mission.discodeit.dto.response.MessageDto;
import com.sprint.mission.discodeit.dto.response.PageResponse;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(EndPoints.MESSAGE)
@RequiredArgsConstructor
public class MessageController implements MessageApi {

  private final MessageService messageService;

  // 메시지 보내기(생성)
  @Override
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Message> create(
      @RequestPart("messageCreateRequest") MessageCreateRequest dto,
      @RequestPart(value = "attachments", required = false) List<MultipartFile> attachments) {
    return ResponseEntity.status(HttpStatus.CREATED).body(messageService.create(dto, attachments));
  }

  // 메시지 수정
  @Override
  @PatchMapping(value = "/{messageId}")
  public ResponseEntity<Message> update(
      @PathVariable("messageId") UUID id,
      @RequestBody MessageUpdateRequest dto) {
    return ResponseEntity.ok(messageService.update(id, dto));
  }

  // 메시지 삭제
  @Override
  @DeleteMapping(value = "/{messageId}")
  public ResponseEntity<Void> delete(
      @PathVariable("messageId") UUID id) {
    messageService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // 특정 채널의 모든 메시지를 조회
  @Override
  @GetMapping
  public ResponseEntity<PageResponse<MessageDto>> readAllByChannelId(
      @RequestParam("channelId") UUID channelId,
      @RequestParam(value = "cursor", required = false) Instant cursor) {
    return ResponseEntity.ok(messageService.findAllByChannelId(channelId, cursor));
  }
}