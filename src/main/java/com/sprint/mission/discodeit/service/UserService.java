package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public interface UserService {
    // 코드 탬플릿에 맞게 create 메서드 수정
    User create(String name, String email, String password);
//    void createUser(User user);

    User readAll(UUID id);

    User updateName(UUID id, String newName);

    User updateNickname(UUID id, String newNickname);

    User updateEmail(UUID id, String newEmail);

    User updatePhoneNumber(UUID id, String newPhoneNumber);

    User updateStatus(UUID id, User.Status newStatus);

    void delete(UUID id);
}
