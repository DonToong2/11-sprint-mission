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
    private final String writer; // 메시지 작성자, 변경 불가
    private final String channel; // 메시지가 작성된 채널, 변경 불가

    public Message(String content, String writer, String channel) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = this.createdAt;

        this.content = content;

        this.writer = writer;
        this.channel = channel;
    }

    // getter
    public UUID getId() { return id; }
    public long getCreatedAt() { return createdAt; }
    public long getUpdatedAt() { return updatedAt; }
    public String getContent() { return content; }
    public String getWriter() { return writer; }
    public String getChannel() { return channel; }

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
                + "\n 메시지 작성자 : " + writer
                + ", 메시지가 작성된 채널 : " + channel;
    }
}