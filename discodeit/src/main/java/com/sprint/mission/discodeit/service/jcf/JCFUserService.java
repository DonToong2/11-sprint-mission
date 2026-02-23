package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFUserService implements UserService {

    private final Map<UUID, User> users = new HashMap<>();

    @Override
    public void createUser(User user) {
        users.put(user.getId(), user);
        System.out.println("===== 유저 추가 =====\n" + user);
    }

    @Override
    public void readUser(UUID id) {
        User user = users.get(id);
        System.out.println("유저 검색 : " + user.getId());
    }

    @Override
    public void updateUser(User user) { users.put(user.getId(), user); }

    @Override
    public void deleteUser(UUID id) {
        User user = users.get(id);
        users.remove(id);
    }
}
