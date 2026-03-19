package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserStatusRepository;

import java.util.*;

public class JCFUserStatusRepository implements UserStatusRepository {
    private final Map<UUID, UserStatus> userStatuses = new HashMap<>();

    @Override
    public void insert(UserStatus userStatus) {
        userStatuses.put(userStatus.getId(), userStatus);
    }

    @Override
    public UserStatus findById(UUID id) {
        UserStatus userStatus = userStatuses.get(id);
        if (userStatus == null) {
            throw new NoSuchElementException("해당 UserStatus가 존재하지 않습니다 id : " + id);
        }

        return userStatus;
    }

    @Override
    public UserStatus findByUserId(UUID userId) {
        return userStatuses.values().stream()
                .filter(userStatus -> userStatus.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당 UserStatus가 존재하지 않습니다 id : " + userId));
    }

    @Override
    public List<UserStatus> findAll() {
        return userStatuses.values().stream().toList();
    }

    @Override
    public void update(UserStatus userStatus) {
        userStatuses.put(userStatus.getId(), userStatus);
    }

    @Override
    public void delete(UUID id) {
        userStatuses.remove(id);
    }

    @Override
    public void deleteByUserId(UUID userId) {
        userStatuses.values().removeIf(status -> status.getUserId().equals(userId));
    }
}
