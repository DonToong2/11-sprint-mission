package com.sprint.mission.discodeit.controller;

import com.sprint.mission.discodeit.constant.EndPoints;
import com.sprint.mission.discodeit.dto.UserCreateRequest;
import com.sprint.mission.discodeit.dto.UserReadDto;
import com.sprint.mission.discodeit.dto.UserUpdateRequest;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.UserStatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


// 사용자 관리 컨트롤러
@RestController // Json 반환을 위해 @Controller 대신 @ResponseBody를 포함한 @RestController 사용
@RequestMapping(EndPoints.USER)
@Tag(name = "User", description = "User API")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService; // 특정 유저 생성, 모든 유저 조회, 특정 유저 업데이트, 특정 유저 삭제
  private final UserStatusService userStatusService; // 특정 유저의 상태 업데이트

  // 특정 사용자 등록
  @ResponseStatus(HttpStatus.CREATED)
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<User> create(
      @RequestPart("userCreateRequest") UserCreateRequest dto,
      @RequestPart(value = "profile", required = false) MultipartFile profile) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(dto, profile));
  }

  // 특정 사용자 정보 수정
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
  public ResponseEntity<User> update(@PathVariable("userId") UUID id,
      @RequestPart("userUpdateRequest") UserUpdateRequest dto,
      @RequestPart(value = "profile", required = false) MultipartFile profile) {
    return ResponseEntity.ok(userService.update(id, dto, profile));
  }

  // 특정 사용자 삭제
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable("userId") UUID id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // 모든 사용자 조회, 심화 요구사항 추가
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<UserReadDto>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<UserReadDto>> readAll() {
//        return ResponseEntity.ok(userService.findAll());
//    }

  // 특정 사용자의 상태(온/오프라인) 업데이트
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/{userId}/userStatus", method = RequestMethod.PATCH)
  public ResponseEntity<UserStatus> updateStatus(@PathVariable("userId") UUID id) {
    return ResponseEntity.ok(userStatusService.updateByUserId(id));
  }


}
