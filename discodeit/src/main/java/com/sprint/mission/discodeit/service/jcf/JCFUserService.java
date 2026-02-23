package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFUserService implements UserService {

    private final Map<UUID, User> users = new HashMap<>();

    // Create
    @Override
    public void createUser(User user) {
        users.put(user.getId(), user);
        System.out.println("유저를 추가하였습니다.");
    }


    // Read
    @Override
    public void readUserName(UUID id) {
        User user = users.get(id);
        System.out.println("유저 이름 : " + user.getName());
    }
    @Override
    public void readUserNickname(UUID id) {
        User user = users.get(id);
        System.out.println("유저 별명 : " + user.getNickname());
    }
    @Override
    public void readUserEmail(UUID id) {
        User user = users.get(id);
        System.out.println("유저 이메일 : " + user.getEmail());
    }
    @Override
    public void readUserPhoneNumber(UUID id) {
        User user = users.get(id);
        System.out.println("유저 휴대폰 번호 : " + user.getPhoneNumber());
    }
    @Override
    public void readUserProfileImageURL(UUID id) {
        User user = users.get(id);
        System.out.println("유저 프로필 이미지 : " + user.getProfileImageURL());
    }
    @Override
    public void readUserStatus(UUID id) {
        User user = users.get(id);
        System.out.println("유저 상태 : " + user.getUserStatus());
    }
    @Override
    public void readUserAll(UUID id) {
        User user = users.get(id);
        System.out.println("=====유저 정보=====\n" + user);
    }


    // Update
    // 같은 키, 다른 Value를 put 하면 키는 그대로, Value만 갱신된다.
    @Override
    public void updateUserName(UUID id, String newName) {
        User user = users.get(id);
        System.out.println("수정 전 유저 이름 : " + user.getName());
        user.updateName(newName);
        System.out.println("수정 후 유저 이름 : " + user.getName());
    }
    @Override
    public void updateUserNickname(UUID id, String newNickname) {
        User user = users.get(id);
        System.out.println("수정 전 유저 별명 : " + user.getNickname());
        user.updateNickname(newNickname);
        System.out.println("수정 후 유저 별명 : " + user.getNickname());
    }
    @Override
    public void updateUserEmail(UUID id, String newEmail) {
        User user = users.get(id);
        System.out.println("수정 전 유저 이메일 : " + user.getEmail());
        user.updateEmail(newEmail);
        System.out.println("수정 후 유저 이메일 : " + user.getEmail());
    }
    @Override
    public void updatePhoneNumber(UUID id, String newPhoneNumber) {
        User user = users.get(id);
        System.out.println("수정 전 유저 전화번호 : " + user.getPhoneNumber());
        user.updatePhoneNumber(newPhoneNumber);
        System.out.println("수정 후 유저 전화번호 : " + user.getPhoneNumber());
    }
    @Override
    public void updateUserProfileImageURL(UUID id, String newProfileImageURL) {
        User user = users.get(id);
        System.out.println("수정 전 유저 프로필 이미지 : " + user.getProfileImageURL());
        user.updateProfileImageURL(newProfileImageURL);
        System.out.println("수정 후 유저 프로필 이미지 : " + user.getProfileImageURL());
    }
    @Override
    public void updateUserStatus(UUID id, User.Status newStatus) {
        User user = users.get(id);
        System.out.println("수정 전 유저 상태 : " + user.getUserStatus());
        user.updateStatus(newStatus);
        System.out.println("수정 후 유저 상태 : " + user.getUserStatus());
    }

    // Delete
    @Override
    public void deleteUser(UUID id) {
        User user = users.get(id);
        users.remove(id);
        if (user == null) {
            System.out.println("해당 유저는 존재하지 않습니다.");
        }
        else {
            System.out.println("유저 " + user.getNickname() + "이(가) 삭제되었습니다.");
        }
    }
}
