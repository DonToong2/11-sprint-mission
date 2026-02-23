package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class Message {
    private final UUID id;
    private final long createdAt;
    private long updatedAt;
    private String content;
    private User writer;
    private Channel channel;

    public Message(String content, User writer, Channel channel) {
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
    public User getWriter() { return writer; }
    public Channel channel() { return channel; }

    // update
    private void update() {
        this.updatedAt = System.currentTimeMillis();
    }
    public void updateContent(String content) {
        this.content = content;
        update();
    }
    public void updateWriter(User writer) {
        this.writer = writer;
        update();
    }
    public void updateChannel(Channel channel) {
        this.channel = channel;
        update();
    }
}