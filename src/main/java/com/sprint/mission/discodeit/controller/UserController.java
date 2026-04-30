package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.constant.EndPoints;
import com.sprint.mission.discodeit.controller.api.UserApi;
import com.sprint.mission.discodeit.dto.request.UserCreateRequest;
import com.sprint.mission.discodeit.dto.request.UserStatusUpdateRequest;
import com.sprint.mission.discodeit.dto.request.UserUpdateRequest;
import com.sprint.mission.discodeit.dto.response.UserDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.UserStatusService;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController // Json 반환을 위해 @Controller 대신 @ResponseBody를 포함한 @RestController 사용
@RequestMapping(EndPoints.USER)
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserService userService; // 특정 유저 생성, 모든 유저 조회, 특정 유저 업데이트, 특정 유저 삭제
  private final UserStatusService userStatusService; // 특정 유저의 상태 업데이트

  // 특정 사용자 등록
  @Override
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<User> create(
      @RequestPart("userCreateRequest") UserCreateRequest dto,
      @RequestPart(value = "profile", required = false) MultipartFile profile) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto, profile));
  }

  // 특정 사용자 정보 수정
  @Override
  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<User> update(
      @PathVariable("userId") UUID id,
      @RequestPart("userUpdateRequest") UserUpdateRequest dto,
      @RequestPart(value = "profile", required = false) MultipartFile profile) {
    return ResponseEntity.ok(userService.update(id, dto, profile));
  }

  // 특정 사용자 삭제
  @Override
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> delete(
      @PathVariable("userId") UUID id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // 모든 사용자 조회, 심화 요구사항 추가
  @Override
  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public ResponseEntity<List<UserDto>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<UserReadDto>> readAll() {
//        return ResponseEntity.ok(userService.findAll());
//    }

  // 특정 사용자의 상태(온/오프라인) 업데이트
  @Override
  @ResponseStatus(HttpStatus.OK)
  @PatchMapping("/{userId}/userStatus")
  public ResponseEntity<UserStatus> updateStatus(
      @PathVariable("userId") UUID id,
      @RequestBody UserStatusUpdateRequest dto) {
    return ResponseEntity.ok(userStatusService.updateByUserId(id, dto));
  }


}
