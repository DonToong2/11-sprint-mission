package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFUserRepository implements UserRepository {
    private final Map<UUID, User> users = new HashMap<>(); // 저장소
    @Override
    public void insertUser(User user) { users.put(user.getId(), user); }

    @Override
    public boolean isExistsUser(UUID id) { return users.containsKey(id); }

    @Override
    public User findUser(UUID id) {
        return users.get(id);
    }

    @Override
    public void updateUser(User user) {
    }

    @Override
    public void deleteUser(UUID id) {
        users.remove(id);
    }
}
