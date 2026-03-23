package com.sprint.mission.discodeit.Controller;

import com.sprint.mission.discodeit.dto.MessageCreateDto;
import com.sprint.mission.discodeit.dto.MessageUpdateDto;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    // 메시지 보내기(생성)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Message> create(@RequestBody MessageCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.create(dto));
    }

    // 메시지 수정
    @RequestMapping(value = "/{message-id}", method = RequestMethod.PUT)
    public ResponseEntity<Message> update(@PathVariable("message-id") UUID id, @RequestBody MessageUpdateDto dto) {
        return ResponseEntity.ok(messageService.update(id, dto));
    }

    // 메시지 삭제
    @RequestMapping(value = "/{message-id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("message-id") UUID id) {
        messageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 특정 채널의 모든 메시지를 조회
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Message>> readAllByChannelId(@RequestParam("channel-id") UUID channelId) {
        return ResponseEntity.ok(messageService.findAllByChannelId(channelId));
    }
}