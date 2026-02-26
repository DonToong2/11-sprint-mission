package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFUserService implements UserService {

    private final Map<UUID, User> users = new HashMap<>(); // 저장 로직

    // Create
    @Override
    public void createUser(User user) {
        users.put(user.getId(), user); // 저장 로직
        System.out.println("유저를 추가하였습니다.");
        System.out.println();
    }


    // Read
    @Override
    public void readUserAll(UUID id) {
        // NPE 방지
        if (!users.containsKey(id)) { System.out.println("해당 유저가 존재하지 않습니다."); }
        else {
            User user = users.get(id);
            System.out.println("=====유저 정보=====\n" + user); }

        System.out.println();
    }


    // Update
    // 같은 키, 다른 Value를 put 하면 키는 그대로, Value만 갱신된다.
    @Override
    public void updateUserName(UUID id, String newName) {
        // NPE 방지
        if (!users.containsKey(id)) { System.out.println("해당 유저가 존재하지 않습니다."); }
        else {
            User user = users.get(id);
            System.out.println("수정 전 유저 이름 : " + user.getName());
            user.updateName(newName);
            System.out.println("수정 후 유저 이름 : " + user.getName());

        }
        System.out.println();
    }

    @Override
    public void updateUserNickname(UUID id, String newNickname) {
        // NPE 방지
        if (!users.containsKey(id)) { System.out.println("해당 유저가 존재하지 않습니다."); }
        else {
            User user = users.get(id);
            System.out.println("수정 전 유저 별명 : " + user.getNickname());
            user.updateNickname(newNickname);
            System.out.println("수정 후 유저 별명 : " + user.getNickname());
        }
        System.out.println();
    }

    @Override
    public void updateUserEmail(UUID id, String newEmail) {
        // NPE 방지
        if (!users.containsKey(id)) { System.out.println("해당 유저가 존재하지 않습니다."); }
        else {
            User user = users.get(id);
            System.out.println("수정 전 유저 이메일 : " + user.getEmail());
            user.updateEmail(newEmail);
            System.out.println("수정 후 유저 이메일 : " + user.getEmail());
        }
        System.out.println();
    }

    @Override
    public void updatePhoneNumber(UUID id, String newPhoneNumber) {
        // NPE 방지
        if (!users.containsKey(id)) { System.out.println("해당 유저가 존재하지 않습니다."); }
        else {
            User user = users.get(id);
            System.out.println("수정 전 유저 전화번호 : " + user.getPhoneNumber());
            user.updatePhoneNumber(newPhoneNumber);
            System.out.println("수정 후 유저 전화번호 : " + user.getPhoneNumber());
        }
        System.out.println();
    }

    @Override
    public void updateUserProfileImageURL(UUID id, String newProfileImageURL) {
        // NPE 방지
        if (!users.containsKey(id)) { System.out.println("해당 유저가 존재하지 않습니다."); }
        else {
            User user = users.get(id);
            System.out.println("수정 전 유저 프로필 이미지 : " + user.getProfileImageURL());
            user.updateProfileImageURL(newProfileImageURL);
            System.out.println("수정 후 유저 프로필 이미지 : " + user.getProfileImageURL());
        }
        System.out.println();
    }

    @Override
    public void updateUserStatus(UUID id, User.Status newStatus) {
        // NPE 방지
        if (!users.containsKey(id)) { System.out.println("해당 유저가 존재하지 않습니다."); }
        else {
            User user = users.get(id);
            System.out.println("수정 전 유저 상태 : " + user.getUserStatus());
            user.updateStatus(newStatus);
            System.out.println("수정 후 유저 상태 : " + user.getUserStatus());
        }
        System.out.println();
    }

    // Delete
    @Override
    public void deleteUser(UUID id) {
        User user = users.get(id);
        if (!users.containsKey(id)) { System.out.println("해당 유저는 존재하지 않습니다."); }
        else {
            System.out.println("유저 " + user.getNickname() + "이(가) 삭제되었습니다.");
            users.remove(id);
        }
        System.out.println();
    }

}
