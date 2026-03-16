package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFChannelRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFMessageRepository;
import com.sprint.mission.discodeit.repository.jcf.JCFUserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.BasicChannelService;
import com.sprint.mission.discodeit.service.basic.BasicMessageService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DiscodeitApplication {
	static User setupUser(UserService userService) {
		User user = userService.create("woody", "woody@codeit.com", "woody1234"); // 유저 이름, 유저 이메일, 유저 비밀번호
		return user;
	}

	static Channel setupChannel(ChannelService channelService) {
		Channel channel = channelService.create(Channel.Type.PUBLIC, "공지", "공지 채널입니다."); // 채널 공개여부, 채널 이름, 채널 설명
		return channel;
	}

	static void messageCreateTest(MessageService messageService, Channel channel, User author) {
		Message message = messageService.create("안녕하세요.", channel.getId(), author.getId()); // 메시지 내용, 채널 id, 작성자(유저) id
		System.out.println("메시지 생성: " + message.getId());
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(DiscodeitApplication.class, args);

		// JCF Repository
		UserRepository userRepository = new JCFUserRepository();
		ChannelRepository channelRepository = new JCFChannelRepository();
		MessageRepository messageRepository = new JCFMessageRepository();

		// File Repository
//        UserRepository userRepository = new FileUserRepository();
//        ChannelRepository channelRepository = new FileChannelRepository();
//        MessageRepository messageRepository = new FileMessageRepository();

		// Basic Service
		// 서비스 초기화
		UserService userService = context.getBean(UserService.class);
		ChannelService channelService = context.getBean(ChannelService.class);
		MessageService messageService = context.getBean(MessageService.class);

		// 셋업
		User user = setupUser(userService);
		Channel channel = setupChannel(channelService);
		// 테스트
		messageCreateTest(messageService, channel, user);


	}
}