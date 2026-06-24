package com.sprint.mission.discodeit.mapper;

import com.sprint.mission.discodeit.dto.response.UserDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.security.DiscodeitUserDetails;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final BinaryContentMapper binaryContentMapper;

  private final SessionRegistry sessionRegistry;

  public UserDto toDto(User user) {
    return new UserDto(
        user.getId(), // id
        user.getUsername(), // username
        user.getEmail(), // email
        user.getProfile() != null ? // profile(BinaryContentDto)
            binaryContentMapper.toDto(user.getProfile()) : null, // 프로필 이미지가 있을수도 없을수도 있음
        isOnline(user.getId()),
        // UserStatus가 존재하고 Status.ONLINE를 가지면 프론트엔드상에서 true(온라인)를, 그렇지 않으면 false(오프라인)를 반환
        user.getRole()
    );
  }

  public boolean isOnline(UUID userId) {

    return sessionRegistry.getAllPrincipals().stream()
        .filter(principal -> principal instanceof DiscodeitUserDetails)
        .map(principal -> (DiscodeitUserDetails) principal)
        .anyMatch(userDetails ->
            userDetails.getUserDto().id().equals(userId)
                && !sessionRegistry.getAllSessions(userDetails, false).isEmpty()
        );
  }

}
