package com.sprint.mission.discodeit.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Channel implements Serializable {

    // 객체 직렬화
    private static final long serialVersionUID = 1L;

    // 필수
    private final UUID id;
    private final long createdAt;
    private long updatedAt;

    // 어디 그룹에 속한 채널인가
    private String group; // 채널 그룹
    private String name; // 채널 이름
    private List<String> members; // 채널 멤버

    // 코드 탬플릿에 맞게 필드 추가
    private ChannelType channelType;
    private String description;

    public enum ChannelType {
        PUBLIC, PRIVATE;
    }

    // 생성자
    public Channel(String group, String name, List<String> members) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = this.createdAt;
        this.group = group;
        this.name = name;
        this.members = members;
    }

    // 정적 팩토리 메서드
    // 코드 탬플릿에 적합한 생성자 오버로딩
    private Channel(ChannelType channelType, String name, String description) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = this.createdAt;
        this.channelType = channelType;
        this.name = name;
        this.description = description;
    }

    // 정적 팩토리 메서드
    public static Channel create(ChannelType channelType, String name, String description) {
        return new Channel(channelType, name, description);
    }

    // getter
    public UUID getId() {
        return id;
    }
    public long getCreatedAt() {
        return createdAt;
    }
    public long getUpdatedAt() {
        return updatedAt;
    }
    public String getGroup() {
        return group;
    }
    public String getName() {
        return name;
    }
    public List<String> getMembers() {
        return members;
    }

    // update(set)
    private void update() {
        this.updatedAt = System.currentTimeMillis();
    }
    public void updateGroup(String group) {
        this.group = group;
        update();
    }
    public void updateName(String name) {
        this.name = name;
        update();
    }
    public void updateMember(List<String> members) {
        this.members = members;
        update();
    }

    @Override
    public String toString() {
        String memberNames = members.stream()
                .collect(Collectors.joining(",", "[", "]"));

        return "유저 UUID : " + id
                + "\n 생성 시간 : " + createdAt + ", 수정한 시간 : " + updatedAt
                + "\n 채널 이름 : " + name + ", 채널이 속해있는 그룹 : " + group
                + "\n 채널 멤버 : " + memberNames;
    }
}
