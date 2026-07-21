package com.sprint.mission.discodeit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {

    // 클라이언트가 서버의 메시지를 구독하는 경로 접두사
    registry.enableSimpleBroker("/sub");

    // 클라이언트에서 메시지를 서버로 발행하는 경로 접두사
    registry.setApplicationDestinationPrefixes("/pub");
    
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {

    // "/ws"에서 WebSocket, SockJS 클라이언트 연결을 지원
    registry.addEndpoint("/ws").withSockJS();

  }

}
