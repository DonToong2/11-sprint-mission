package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public interface UserRepository {

    void insert(User user);

    User findById(UUID id);

    void update(User user);

    void delete(UUID id);
}