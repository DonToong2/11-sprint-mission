package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.constant.EndPoints;
import com.sprint.mission.discodeit.controller.api.ChannelApi;
import com.sprint.mission.discodeit.dto.request.ChannelCreatePrivateRequest;
import com.sprint.mission.discodeit.dto.request.ChannelCreatePublicRequest;
import com.sprint.mission.discodeit.dto.request.ChannelUpdateRequest;
import com.sprint.mission.discodeit.dto.response.ChannelDto;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoints.CHANNEL)
@RequiredArgsConstructor
public class ChannelController implements ChannelApi {

  private final ChannelService channelService;

  // 공개 채널 생성
  @Override
  @RequestMapping(value = "/public", method = RequestMethod.POST)
  @PostMapping("/public")
  public ResponseEntity<Channel> createPublic(@RequestBody ChannelCreatePublicRequest dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(channelService.createPublic(dto));
  }

  // 비공개 채널 생성
  @Override
  @PostMapping("/private")
  public ResponseEntity<Channel> createPrivate(@RequestBody ChannelCreatePrivateRequest dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(channelService.createPrivate(dto));
  }

  // 특정 공개 채널의 정보 수정(channels/{channelId}?)
  @Override
  @PatchMapping("/{channelId}")
  public ResponseEntity<Channel> updatePublic(
      @PathVariable("channelId") UUID id,
      @RequestBody ChannelUpdateRequest dto) {
    return ResponseEntity.ok(channelService.update(id, dto));
  }

  // 특정 채널 삭제(channels/{channelId})
  @Override
  @DeleteMapping("/{channelId}")
  public ResponseEntity<Void> delete(
      @PathVariable("channelId") UUID id) {
    channelService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // 특정 사용자가 속한 모든 채널 목록 조회(channels?user-id=...)
  @Override
  @GetMapping
  public ResponseEntity<List<ChannelDto>> readAllByUser(
      @RequestParam("userId") UUID userId) {
    return ResponseEntity.ok(channelService.findAllByUserId(userId));
  }
}
