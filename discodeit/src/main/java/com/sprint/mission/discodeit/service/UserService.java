package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public interface UserService {
    void createUser(User user);
    void readUser(UUID id);
    void updateUser(User user);
    void deleteUser(UUID id);
}
