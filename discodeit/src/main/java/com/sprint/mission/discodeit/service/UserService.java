package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public interface UserService {

    void createUser(User user);

    void readUserAll(UUID id);

    void updateUserName(UUID id, String newName);
    void updateUserNickname(UUID id, String newNickname);
    void updateUserEmail(UUID id, String newEmail);
    void updatePhoneNumber(UUID id, String newPhoneNumber);
    void updateUserProfileImageURL(UUID id, String newProfileImageURL);
    void updateUserStatus(UUID id, User.Status newStatus);

    void deleteUser(UUID id);
}
