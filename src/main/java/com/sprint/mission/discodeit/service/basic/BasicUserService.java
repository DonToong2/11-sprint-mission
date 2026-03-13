package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.UserService;

import java.util.UUID;

public class BasicUserService implements UserService {
    private final UserRepository userRepository;

    public BasicUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create
    @Override
    public User create(String name, String email, String password) {
        User user = User.create(name, email, password);
        userRepository.insert(user);
        System.out.println("유저를 추가하였습니다.");
        System.out.println();

        return user;
    }


    // Read
    @Override
    public User readAll(UUID id) {
        User user = userRepository.findById(id);
        System.out.println("=====유저 정보=====\n" + user);
        System.out.println();

        return user;
    }


    // Update
    // 같은 키, 다른 Value를 put 하면 키는 그대로, Value만 갱신된다.
    @Override
    public User updateName(UUID id, String newName) {
        User user = userRepository.findById(id);
        System.out.println("수정 전 유저 이름 : " + user.getName());
        user.updateName(newName);
        System.out.println("수정 후 유저 이름 : " + user.getName());
        userRepository.update(user);
        System.out.println();

        return user;
    }

    @Override
    public User updateNickname(UUID id, String newNickname) {
        User user = userRepository.findById(id);
        System.out.println("수정 전 유저 별명 : " + user.getNickname());
        user.updateNickname(newNickname);
        System.out.println("수정 후 유저 별명 : " + user.getNickname());
        userRepository.update(user);
        System.out.println();

        return user;
    }

    @Override
    public User updateEmail(UUID id, String newEmail) {
        User user = userRepository.findById(id);
        System.out.println("수정 전 유저 이메일 : " + user.getEmail());
        user.updateEmail(newEmail);
        System.out.println("수정 후 유저 이메일 : " + user.getEmail());
        userRepository.update(user);
        System.out.println();

        return user;
    }

    @Override
    public User updatePhoneNumber(UUID id, String newPhoneNumber) {
        User user = userRepository.findById(id);
        System.out.println("수정 전 유저 전화번호 : " + user.getPhoneNumber());
        user.updatePhoneNumber(newPhoneNumber);
        System.out.println("수정 후 유저 전화번호 : " + user.getPhoneNumber());
        userRepository.update(user);
        System.out.println();

        return user;
    }

    @Override
    public User updateProfileImageURL(UUID id, String newProfileImageURL) {
        User user = userRepository.findById(id);
        System.out.println("수정 전 유저 프로필 이미지 : " + user.getProfileImageURL());
        user.updateProfileImageURL(newProfileImageURL);
        System.out.println("수정 후 유저 프로필 이미지 : " + user.getProfileImageURL());
        userRepository.update(user);
        System.out.println();

        return user;
    }

    @Override
    public User updateStatus(UUID id, User.UserStatus newStatus) {
        User user = userRepository.findById(id);
        System.out.println("수정 전 유저 상태 : " + user.getUserStatus());
        user.updateStatus(newStatus);
        System.out.println("수정 후 유저 상태 : " + user.getUserStatus());
        userRepository.update(user);
        System.out.println();

        return user;
    }

    // Delete
    @Override
    public void delete(UUID id) {
        User user = userRepository.findById(id);
        userRepository.delete(id);
        System.out.println("유저 " + user.getNickname() + "이(가) 삭제되었습니다.");
        System.out.println();
    }
}
