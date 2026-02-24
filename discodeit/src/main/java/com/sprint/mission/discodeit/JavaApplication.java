package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.ArrayList;
import java.util.List;

public class JavaApplication {
    public static void main(String[] args) {
        JCFUserService userService = new JCFUserService();
        JCFChannelService channelService = new JCFChannelService();

        System.out.println("=====유저 서비스 테스트=====");
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

        // 유저가 삭제되었는지 삭제 시도 후 확인
        userService.deleteUser(user1.getId());


        System.out.println("=====채널 서비스 테스트=====");
        List<String> members1 = new ArrayList<>();
        Channel channel1 = new Channel(
                "자유",
                "소통",
                members1
        );


        // 채널 생성
        channelService.createChannel(channel1);

        // 채널 조회
        channelService.readChannelName(channel1.getId());
        channelService.readChannelGroup(channel1.getId());
        channelService.readChannelMembers(channel1.getId());
        channelService.readChannelAll(channel1.getId());


        // 채널 수정
        channelService.updateChannelGroup(channel1.getId(), "스터디 그룹");
        channelService.updateChannelName(channel1.getId(), "스터디1");
        channelService.updateChannelMembersAdd(channel1.getId(), user1.getName());

        channelService.readChannelAll(channel1.getId()); // 수정 시각 확인을 위한 코드
        channelService.updateChannelMembersRemove(channel1.getId(), user1.getName());
        channelService.readChannelAll(channel1.getId()); // 수정 시각 확인을 위한 코드


        // 채널 삭제
        channelService.deleteChannel(channel1.getId());
        channelService.deleteChannel(channel1.getId());
    }
}
