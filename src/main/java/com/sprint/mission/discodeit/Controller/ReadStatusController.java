package com.sprint.mission.discodeit.Controller;

import com.sprint.mission.discodeit.dto.ReadStatusCreateDto;
import com.sprint.mission.discodeit.dto.ReadStatusUpdateDto;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.service.ReadStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/readStatus")
@RequiredArgsConstructor
public class ReadStatusController {
    private final ReadStatusService readStatusService;

    // 특정 채널의 메시지 수신 정보 생성
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ReadStatus> create(@RequestBody ReadStatusCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(readStatusService.create(dto));
    }

    // 특정 채널의 메시지 수신 정보 수정
    @RequestMapping(value = "/{readStatus-id}", method = RequestMethod.PUT)
    public ResponseEntity<ReadStatus> update(@PathVariable("readStatus-id") UUID id, @RequestBody ReadStatusUpdateDto dto) {
        return ResponseEntity.ok(readStatusService.update(id, dto));
    }

    // 특정 사용자의 메시지 수신 정보 조회
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ReadStatus>> readMessageByUserId(@RequestParam("user-id") UUID userId) {
        return ResponseEntity.ok(readStatusService.findAllByUserId(userId));
    }
}
