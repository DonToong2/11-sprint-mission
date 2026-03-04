package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public interface UserService {
    // 코드 탬플릿에 맞게 create 메서드 수정
    User create(String name, String email, String password);
//    void createUser(User user);

    void readUserAll(UUID id);

    void updateUserName(UUID id, String newName);
    void updateUserNickname(UUID id, String newNickname);
    void updateUserEmail(UUID id, String newEmail);
    void updatePhoneNumber(UUID id, String newPhoneNumber);
    void updateUserProfileImageURL(UUID id, String newProfileImageURL);
    void updateUserStatus(UUID id, User.Status newStatus);

    void deleteUser(UUID id);
}
