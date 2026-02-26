package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFChannelRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFUserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.file.FileChannelService;
import com.sprint.mission.discodeit.service.file.FileMessageService;
import com.sprint.mission.discodeit.service.file.FileUserService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JavaApplication {
    public static void main(String[] args) {
        UserRepository userRepository = new JCFUserRepository();
        ChannelRepository channelRepository = new JCFChannelRepository();

        UserService userService = new JCFUserService(userRepository);
        ChannelService channelService = new JCFChannelService(channelRepository);

//        ChannelService channelService = new FileChannelService();
        MessageService messageService = new FileMessageService();

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
//        // JCF
        userService.readUserAll(user1.getId());

        // File
        // 조회용 UUID
//        String userNickname0 = "DonToong"; // 아직 존재하지 않는 유저 -> Update 메서드 후 생성
//        String userNickname1 = "GilDong"; // Update 메서드 후 사라짐
//        String userNickname2 = "Mugae";
//        UUID userId0 = ((FileUserService) userService).findIdByNickname(userNickname0);
//        UUID userId1 = ((FileUserService) userService).findIdByNickname(userNickname1);
//        UUID userId2 = ((FileUserService) userService).findIdByNickname(userNickname2);
//
//        userService.readUserAll(userId1);
//        userService.readUserAll(userId2);
//
//        // (테스트용) 없는 닉네임의 User 검색
//        userService.readUserAll(userId0);


        // ========== 유저 수정 ========== //
        // JCF
        userService.updateUserName(user1.getId(), "김명근");
        userService.updateUserNickname(user1.getId(), "DonToong");
        userService.updateUserEmail(user1.getId(), "rlaaudrms369@naver.com");
        userService.updatePhoneNumber(user1.getId(), "010-5009-8324");
        userService.updateUserProfileImageURL(user1.getId(), "dog");
        userService.updateUserStatus(user1.getId(), User.Status.ONLINE);

        userService.readUserAll(user1.getId()); // 수정 시각 체크 및 수정 확인

        // File
//        userService.updateUserName(userId1, "김명근");
//        userService.updateUserNickname(userId1, "DonToong");
//        userService.updateUserEmail(userId1, "rlaaudrms369@naver.com");
//        userService.updatePhoneNumber(userId1, "010-5009-8324");
//        userService.updateUserProfileImageURL(userId1, "dog");
//        userService.updateUserStatus(userId1, User.Status.ONLINE);
//
//        userService.readUserAll(userId1); // 수정 시각 체크 및 수정 확인

        // ========== 유저 삭제 ========== //
        // JCF
        userService.deleteUser(user1.getId());

        // 유저가 삭제되었는지 삭제 시도 후 확인
        userService.deleteUser(user1.getId());

        // File
//        userService.deleteUser(userId1);

        // 유저가 삭제되었는지 삭제 시도 후 확인
//        userService.deleteUser(userId1);





        System.out.println("==========채널 서비스 테스트==========");
        List<String> members1 = new ArrayList<>();
        Channel channel1 = new Channel(
                "자유",
                "소통",
                members1
        );

        List<String> members2 = new ArrayList<>();
        Channel channel2 = new Channel(
                "음식",
                "바나나",
                members2
        );


        // ========== 채널 생성 ========== //
        channelService.createChannel(channel1);
        channelService.createChannel(channel2);


        // ========== 채널 조회 ========== //
//        // JCF
        channelService.readChannelAll(channel1.getId());

        // File
        // 조회용 UUID
//        String channelName0 = "스터디1"; // 아직 존재하지 않는 채널 -> Update 메서드 후 생성
//        String channelName1 = "소통"; // Update 메서드 후 사라짐
//        String channelName2 = "바나나";
//        UUID channelId0 = ( (FileChannelService) channelService).findIdByName(channelName0);
//        UUID channelId1 = ( (FileChannelService) channelService).findIdByName(channelName1);
//        UUID channelId2 = ( (FileChannelService) channelService).findIdByName(channelName2);
//
//        channelService.readChannelAll(channelId1);
//        channelService.readChannelAll(channelId2);

        // (테스트용) 없는 이름의 채널 검색
//        channelService.readChannelAll(channelId0);


        // ========== 채널 수정 ========== //
//        // JCF
        channelService.updateChannelGroup(channel1.getId(), "스터디 그룹");
        channelService.updateChannelName(channel1.getId(), "스터디1");

        channelService.updateChannelMembersAdd(channel1.getId(), user1.getName());
        channelService.readChannelAll(channel1.getId()); // 채널 수정 시각, 멤버가 추가되었는지 확인

        channelService.updateChannelMembersRemove(channel1.getId(), user1.getName());
        channelService.readChannelAll(channel1.getId()); // 채널 수정 시각, 멤버가 삭제되었는지 확인

        // File
//        channelService.updateChannelGroup(channelId1, "스터디 그룹");
//        channelService.updateChannelName(channelId1, "스터디1");
//
//        channelService.updateChannelMembersAdd(channelId1, "아무개");
//        channelService.updateChannelMembersAdd(channelId1, "김명근");
//        channelService.readChannelAll(channelId1); // 채널 수정 시각, 멤버가 추가되었는지 확인
//
//        channelService.updateChannelMembersRemove(channelId1, "아무개");
//        channelService.readChannelAll(channelId1); // 채널 수정 시각, 멤버가 삭제되었는지 확인


        // ========== 채널 삭제 ========== //
        // JCF
        channelService.deleteChannel(channel1.getId());
        // 채널이 삭제되었는지 삭제 시도 후 확인
        channelService.deleteChannel(channel1.getId());

        // File
//        channelService.deleteChannel(channelId1);

        // 채널이 삭제되었는지 삭제 시도 후 확인
//        channelService.deleteChannel(channelId1);




        System.out.println("==========메시지 서비스 테스트==========");
        Message message1 = new Message("안녕하세요",
                user1.getName(),
                channel1.getName());


        // ========== 메시지 생성 ========== //
        messageService.createMessage(message1);


        // ========== 메시지 조회 ========== //
//        // JCF
//        messageService.readMessageAll(message1.getId());

        // 조회용 UUID
        String messageContent0 = "감사합니다"; // 아직 존재하지 않는 채널 -> Update 메서드 후 생성
        String messageContent1 = "안녕하세요"; // Update 메서드 후 사라짐
        String messageContent2 = "어서오세요";
        UUID messageId0 = ((FileMessageService) messageService).findByContent(messageContent0);
        UUID messageId1 = ((FileMessageService) messageService).findByContent(messageContent1);
        UUID messageId2 = ((FileMessageService) messageService).findByContent(messageContent2);
        
        // File
        messageService.readMessageAll(messageId1);
        messageService.readMessageAll(messageId2);

        // (테스트용) 없는 닉네임의 Message 검색
        messageService.readMessageAll(messageId0);


        // ========== 메시지 수정 ========== //

//        // JCF
//        messageService.updateMessageContent(message1.getId(), "감사합니다");
//        messageService.readMessageAll(message1.getId()); // 수정된 메시지, 최근 수정 시각 확인

        // File
        messageService.updateMessageContent(messageId1, "감사합니다");
        messageService.readMessageAll(messageId1); // 수정된 메시지, 최근 수정 시각 확인


        // ========== 메시지 삭제 ========== //
//        // JCF
//        messageService.deleteMessage(message1.getId());
//
//        // 메시지가 삭제되었는지 다시 삭제 시도 후 확인
//        messageService.deleteMessage(message1.getId());

        // File
        messageService.deleteMessage(messageId1);

        // 메시지가 삭제되었는지 다시 삭제 시도 후 확인
        messageService.deleteMessage(messageId1);
    }
}