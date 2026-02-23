package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.UUID;

public class JavaApplication {
    public static void main(String[] args) {
        JCFUserService userService = new JCFUserService();

        User user1 = new User(
                "김명근",
                "DonToong",
                "rlaaudrms369@gmail.com",
                "010-5009-8324",
                "none",
                User.Status.ONLINE
        );

        userService.createUser(user1);
    }
}
