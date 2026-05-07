package com.sprint.mission.discodeit.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

import com.sprint.mission.discodeit.dto.request.UserCreateRequest;
import com.sprint.mission.discodeit.dto.request.UserUpdateRequest;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.exception.DiscodeitException;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.service.basic.BasicUserService;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private BinaryContentRepository binaryContentRepository;

  @Mock
  private UserStatusRepository userStatusRepository;

  @InjectMocks
  private BasicUserService userService;

  // User Create Success
  @Test
  void create_success() {
    // given
    // 유저 DTO
    UserCreateRequest request = new UserCreateRequest("테스트", "test@naver.com", "12345678");

    // 프로필
    MultipartFile profile = mock(MultipartFile.class);
    given(profile.getOriginalFilename()).willReturn("test.png");
    given(profile.getSize()).willReturn(100L);
    given(profile.getContentType()).willReturn("image/png");
    given(profile.isEmpty()).willReturn(false); // 프로필 이미지 존재하는 유저 생성하도록

    // 현재 이름이 "테스트"와 "test@naver.com"은 없음
    given(userRepository.existsByUsername("테스트")).willReturn(false);
    given(userRepository.existsByEmail("test@naver.com")).willReturn(false);

    // 각 Repository에 저장 후 willAnswer로 결과를 동적으로 가져옴
    given(userRepository.save(any(User.class))).willAnswer(i -> i.getArgument(0));
    given(binaryContentRepository.save(any())).willAnswer(i -> i.getArgument(0));
    given(userStatusRepository.save(any())).willAnswer(i -> i.getArgument(0));

    // when
    User result = userService.create(request, profile);

    // then
    assertThat(result).isNotNull();
    then(userRepository).should().save(any(User.class));
    then(binaryContentRepository).should().save(any());
    then(userStatusRepository).should().save(any());
    // == verify(userStatusRepository).save(any());
  }

  // User Create Fail(이메일 중복)
  // 실패 유도해서 테스트 통과(실패 테스트)
  @Test
  void create_fail() {
    // given
    UserCreateRequest request = new UserCreateRequest("테스트", "test@naver.com", "12345678");

    // "테스트"라는 이름은 아직 없음
    given(userRepository.existsByUsername("테스트")).willReturn(false);

    // 이미 "test@naver.com"이 있음 -> 실패 유도
    given(userRepository.existsByEmail("test@naver.com")).willReturn(true);

    // when & then
    // 예외 시 비즈니스 예외가 나와야함(UserEmailAlreadyExistsException)
    assertThatThrownBy(
        () -> userService.create(request, null)).isInstanceOf(DiscodeitException.class);

    // User 저장되지 않음
    then(userRepository).should(never()).save(any(User.class));
  }

  // User Update Success
  // 이름만 수정
  @Test
  void update_success() {
    // given
    // 유저 생성
    User user = User.create("이름", "test@naver.com", "12345678");

    // 수정 요청
    UserUpdateRequest request = new UserUpdateRequest("새로운 이름", null, null);

    given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

    // when
    User result = userService.update(user.getId(), request, null);

    // then
    assertEquals("새로운 이름", result.getUsername());
//    assertThat(result.getUsername()).isEqualTo("새로운 이름");
    assertThat(result.getEmail()).isEqualTo("test@naver.com");

    // userRepository를 대상으로 save 메서드가 User의 아무 필드나 받아서 호출됐는지 확인
    then(userRepository).should().save(any(User.class));
  }

  // User Update Fail(수정 이름 중복)
  @Test
  void update_fail() {
    // given
    // 유저 생성
    User user = User.create("이름", "test@naver.com", "12345678");

    // 수정 요청
    UserUpdateRequest request = new UserUpdateRequest("새로운 이름", null, null);

    // 유저 조회 시 가짜 객체 존재함을 알림
    given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
    given(userRepository.existsByUsernameAndIdNot("새로운 이름", user.getId())).willReturn(true);

    // when & then
    assertThatThrownBy(() ->
        userService.update(user.getId(), request, null)).isInstanceOf(DiscodeitException.class);

    then(userRepository).should(never()).save(any(User.class));
  }

  // User Delete Success
  @Test
  void delete_success() {
    // given
    User user = User.create("삭제될 유저", "test@naver.com", "12345678");
    given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

    // when
    userService.delete(user.getId());

    // then
    then(userRepository).should().delete(user);
  }

  // User Delete Fail(삭제할 유저가 존재하지 않음)
  @Test
  void delete_fail() {
    // given
    UUID userId = UUID.randomUUID();
    User user = User.create("삭제될 유저", "test@naver.com", "12345678");

    // userId로 조회했을때 빈 값이 나왔을 때
    given(userRepository.findById(userId)).willReturn(Optional.empty());

    // when & then
    assertThatThrownBy(() -> userService.delete(userId)).isInstanceOf(DiscodeitException.class);
    then(userRepository).should(never()).delete(user);
  }
}
