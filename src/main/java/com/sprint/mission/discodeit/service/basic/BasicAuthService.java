package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.request.LoginRequest;
import com.sprint.mission.discodeit.dto.response.UserDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.mapper.UserMapper;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.AuthService;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicAuthService implements AuthService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public UserDto login(LoginRequest request) {
    String username = request.username();
    String password = request.password();

    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new NoSuchElementException("사용자 이름 또는 비밀번호가 일치하지 않습니다."));

    if (!user.getPassword().equals(password)) {
      throw new IllegalArgumentException("패스워트가 틀렸습니다.");
    }

    return userMapper.toDto(user);
  }

}
