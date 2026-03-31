package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.constant.EndPoints;
import com.sprint.mission.discodeit.dto.ReadStatusCreateRequest;
import com.sprint.mission.discodeit.dto.ReadStatusUpdateRequest;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.service.ReadStatusService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoints.READ_STATUS)
@RequiredArgsConstructor
public class ReadStatusController {

  private final ReadStatusService readStatusService;

  // 특정 채널의 메시지 수신 정보 생성
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<ReadStatus> create(@RequestBody ReadStatusCreateRequest dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(readStatusService.create(dto));
  }

  // 특정 채널의 메시지 수신 정보 수정
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/{readStatusId}", method = RequestMethod.PATCH)
  public ResponseEntity<ReadStatus> update(@PathVariable("readStatusId") UUID id,
      @RequestBody ReadStatusUpdateRequest dto) {
    return ResponseEntity.ok(readStatusService.update(id, dto));
  }

  // 특정 사용자의 메시지 수신 정보 조회
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<ReadStatus>> readMessageByUserId(@RequestParam("userId") UUID userId) {
    return ResponseEntity.ok(readStatusService.findAllByUserId(userId));
  }
}
