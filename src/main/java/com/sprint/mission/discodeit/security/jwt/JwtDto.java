package com.sprint.mission.discodeit.security.jwt;

import com.sprint.mission.discodeit.dto.response.UserDto;

public record JwtDto(
    UserDto userDto,
    String accessToken
) {

}
