package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.file.FileUserService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JavaApplication {
    public static void main(String[] args) {
        UserService userService = new JCFUserService();
        ChannelService channelService = new JCFChannelService();
        MessageService messageService = new JCFMessageService();

        System.out.println("==========유저 서비스 테스트==========");
        User user1 = new User(
                "홍길동",
                "GilDong",
                "guildong@gmail.com",
                "010-1234-5678",
                "none",
                User.Status.AWAY
        );
        User user2 = new User(
                "아무개",
                "Mugae",
                "Mugae@gmail.com",
                "010-9872-5547",
                "handsome_muage",
                User.Status.ONLINE
        );

        // 유저 등록
        userService.createUser(user1);
        userService.createUser(user2);


        // 유저 조회
//        userService.readUserName(user1.getId());
//        userService.readUserNickname(user1.getId());
//        userService.readUserPhoneNumber(user1.getId());
//        userService.readUserEmail(user1.getId());
//        userService.readUserPhoneNumber(user1.getId());
//        userService.readUserProfileImageURL(user1.getId());
//        userService.readUserStatus(user1.getId());
        userService.readAllUsers();
//
//
//        // 유저 수정
//        userService.updateUserName(user1.getId(), "김명근");
//        userService.updateUserNickname(user1.getId(), "DonToong");
//        userService.updateUserEmail(user1.getId(), "rlaaudrms369@naver.com");
//        userService.updatePhoneNumber(user1.getId(), "010-5009-8324");
//        userService.updateUserProfileImageURL(user1.getId(), "dog");
//        userService.updateUserStatus(user1.getId(), User.Status.ONLINE);
//
//        userService.readUserAll(user1.getId()); // 수정 시각 확인을 위한 코드
//
//
//        // 유저 삭제
//        userService.deleteUser(user1.getId());
//
//        // 유저가 삭제되었는지 삭제 시도 후 확인
//        userService.deleteUser(user1.getId());
//
//
//
//
//
//        System.out.println("==========채널 서비스 테스트==========");
//        List<String> members1 = new ArrayList<>();
//        Channel channel1 = new Channel(
//                "자유",
//                "소통",
//                members1
//        );
//
//
//        // 채널 생성
//        channelService.createChannel(channel1);
//
//
//        // 채널 조회
//        channelService.readChannelName(channel1.getId());
//        channelService.readChannelGroup(channel1.getId());
//        channelService.readChannelMembers(channel1.getId());
//        channelService.readChannelAll(channel1.getId());
//
//
//        // 채널 수정
//        channelService.updateChannelGroup(channel1.getId(), "스터디 그룹");
//        channelService.updateChannelName(channel1.getId(), "스터디1");
//
//        channelService.updateChannelMembersAdd(channel1.getId(), user1.getName());
//        channelService.readChannelAll(channel1.getId()); // 채널 수정 시각, 멤버가 추가되었는지 확인
//
//        channelService.updateChannelMembersRemove(channel1.getId(), user1.getName());
//        channelService.readChannelAll(channel1.getId()); // 채널 수정 시각, 멤버가 삭제되었는지 확인
//
//
//        // 채널 삭제
//        channelService.deleteChannel(channel1.getId());
//        channelService.deleteChannel(channel1.getId());
//
//
//
//
//
//        System.out.println("==========채널 서비스 테스트==========");
//        Message message1 = new Message("안녕하세요",
//                user1.getName(),
//                channel1.getName());
//
//
//        // 메시지 생성
//        messageService.createMessage(message1);
//
//        // 메시지 조회
//        messageService.readMessageContent(message1.getId());
//        messageService.readMessageWriter(message1.getId());
//        messageService.readMessageChannel(message1.getId());
//        messageService.readMessageAll(message1.getId());
//
//        // 메시지 수정
//        messageService.updateMessageContent(message1.getId(), "감사합니다");
//        messageService.readMessageAll(message1.getId()); // 수정된 메시지, 최근 수정 시각 확인
//
//        // 메시지 삭제
//        messageService.deleteMessage(message1.getId());
//
//        // 메시지가 삭제되었는지 다시 삭제 시도 후 확인
//        messageService.deleteMessage(message1.getId());
    }
}