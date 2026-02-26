package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public interface UserRepository {

    void insertUser(User user);
    boolean isExistsUser(UUID id);
    User findUser(UUID id);
    void deleteUser(UUID id);
}
