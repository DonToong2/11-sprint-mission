package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.UUID;

public class JavaApplication {
    public static void main(String[] args) {
        JCFUserService userService = new JCFUserService();

        User user1 = new User(
                "홍길동",
                "GuilDong",
                "guildong@gmail.com",
                "010-1234-5678",
                "none",
                User.Status.AWAY
        );


        // 유저 등록
        userService.createUser(user1);

        // 유저 조회
        userService.readUserName(user1.getId());
        userService.readUserPhoneNumber(user1.getId());
        userService.readUserEmail(user1.getId());
        userService.readUserPhoneNumber(user1.getId());
        userService.readUserProfileImageURL(user1.getId());
        userService.readUserStatus(user1.getId());
        userService.readUserAll(user1.getId());

        // 유저 수정
        userService.updateUserName(user1.getId(), "김명근");
        userService.updateUserNickname(user1.getId(), "DonToong");
        userService.updateUserEmail(user1.getId(), "rlaaudrms369@naver.com");
        userService.updatePhoneNumber(user1.getId(), "010-5009-8324");
        userService.updateUserProfileImageURL(user1.getId(), "dog");
        userService.updateUserStatus(user1.getId(), User.Status.ONLINE);

        userService.readUserAll(user1.getId()); // 수정 시각 확인을 위한 코드

        // 유저 삭제
        userService.deleteUser(user1.getId());

        // 유저가 삭제되었는지 확인
        userService.deleteUser(user1.getId());
    }
}
