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
        System.out.println("git user.name, user.email 일치 테스트.");
        UserService userService = new FileUserService();
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

        // ========== 유저 등록 ========== //
        userService.createUser(user1);
        userService.createUser(user2);


        // ==========유저 조회========== //
        // JCF
//        userService.readUserAll(user1.getId());

        // File
        // 조회용 UUID
        String nick0 = "DonToong"; // 아직 존재하지 않는 유저 -> Update 메서드 후 생성
        String nick1 = "GilDong"; // Update 메서드 후 사라짐
        String nick2 = "Mugae";
        UUID id0 = ((FileUserService) userService).findIdByNickname(nick0);
        UUID id1 = ((FileUserService) userService).findIdByNickname(nick1);
        UUID id2 = ((FileUserService) userService).findIdByNickname(nick2);
        userService.readUserAll(id1);
        userService.readUserAll(id2);

        // (테스트용) 없는 닉네임의 User 검색
        userService.readUserAll(id0);


        // 유저 수정
        // JCF
//        userService.updateUserName(user1.getId(), "김명근");
//        userService.updateUserNickname(user1.getId(), "DonToong");
//        userService.updateUserEmail(user1.getId(), "rlaaudrms369@naver.com");
//        userService.updatePhoneNumber(user1.getId(), "010-5009-8324");
//        userService.updateUserProfileImageURL(user1.getId(), "dog");
//        userService.updateUserStatus(user1.getId(), User.Status.ONLINE);
//
//        userService.readUserAll(user1.getId()); // 수정 시각 체크 및 수정 확인
//
         // File
        userService.updateUserName(id1, "김명근");
        userService.updateUserNickname(id1, "DonToong");
        userService.updateUserEmail(id1, "rlaaudrms369@naver.com");
        userService.updatePhoneNumber(id1, "010-5009-8324");
        userService.updateUserProfileImageURL(id1, "dog");
        userService.updateUserStatus(id1, User.Status.ONLINE);

        userService.readUserAll(id1); // 수정 시각 체크 및 수정 확인

        // ========== 유저 삭제 ========== //
        // JCF
//        userService.deleteUser(user1.getId());
//
//        // 유저가 삭제되었는지 삭제 시도 후 확인
//        userService.deleteUser(user1.getId());

        // File
        userService.deleteUser(id1);

        // 유저가 삭제되었는지 삭제 시도 후 확인
        userService.deleteUser(id1);
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