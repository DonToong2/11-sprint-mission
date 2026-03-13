package com.sprint.mission.discodeit.entity;

import java.io.Serializable;
import java.util.UUID;

public class Message implements Serializable {

    // 객체 직렬화
    private static final long serialVersionUID = 1L;

    // 필수
    private final UUID id;
    private final long createdAt;
    private long updatedAt;

    // 어떤 채널의 멤버가 메시지를 작성하였는가
    private String content; // 메시지 내용
//    private final String writer; // 메시지 작성자, 변경 불가
//    private final String channel; // 메시지가 작성된 채널, 변경 불가

    // 코드 탬플릿에 맞게 필드 수정
    private final Channel channel; // 어떤 채널에 들어가는 메시지인지, Channel과 연결됨
    private final User author; // 어떤 유저가 작성한 메시지인지, User과 연결됨

    // 정적 팩토리 메서드
    private Message(String content, Channel channel, User author) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = this.createdAt;

        this.content = content;

        this.channel = channel;
        this.author = author;
    }

    // 정적 팩토리 메서드
    public static Message create(String content, Channel channel, User author) {
        return new Message(content, channel, author);
    }

    // getter
    public UUID getId() { return id; }
    public long getCreatedAt() { return createdAt; }
    public long getUpdatedAt() { return updatedAt; }
    public String getContent() { return content; }
    public User getAuthor() { return author; }
    public Channel getChannel() { return channel; }

    // update
    private void update() {
        this.updatedAt = System.currentTimeMillis();
    }
    public void updateContent(String content) {
        this.content = content;
        update();
    }

    @Override
    public String toString() {
        return "메시지 UUID : " + id
                + "\n 메시지 생성 시간 : " + createdAt
                + ", 메시지 수정 시간 : " + updatedAt
                + "\n 메시지 내용 : " + content
                + "\n 메시지가 작성된 채널 : " + channel.getName()
                + ", 메시지 작성자 : " + author.getName();
    }
}