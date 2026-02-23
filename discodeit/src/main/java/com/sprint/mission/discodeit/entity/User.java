package com.sprint.mission.discodeit.entity;

import java.util.UUID;

public class User {
    // 공통
    private final UUID id; // 사용자 ID, 값이 변하면 안된다.
    private final long createdAt; // 계정 생성 시간, 값이 변할수 없다.
    private long updatedAt; // 계정 수정 후 시간, 처음에는 계정 생성 시간과 동일
    private String name; // 사용자 이름
    private String nickName; // 사용자 닉네임
    private String email;
    private String phoneNumber;
    private String profileImageURL; // 프로필 사진 주소
    //private boolean isOneLine;
    private Status status; // 디스코드 접속 상태(온라인, 자리비움, 방해 금지, 오프라인)


    public enum Status {
        ONLINE, AWAY, DO_NOT_DISTURB, OFFLINE
    }

    // 'id', 'createdAt'는 생성자에서 초기화하세요.
    public User(String name, String nickName,
                String email, String phoneNumber,
                String profileImageURL, Status status) {
        this.id = UUID.randomUUID();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = this.createdAt;
        this.name = name;
        this.nickName = nickName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImageURL = profileImageURL;
        this.status = status;
    }

    // get메서드
    public UUID getId() { return id; }
    public long getCreatedAt() { return createdAt; } // 만든 시간
    public long getUpdatedAt() { return updatedAt; } // 수정 시간

    public String getName() { return name; }
    public String getNickName() { return nickName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getProfileImageURL() { return profileImageURL; }
    public Status getUserStatus() { return status; }

    // update메서드
    private void update() {
        this.updatedAt = System.currentTimeMillis();
    }

    public void updateName(String name) {
        this.name = name;
        update();
    }
    public void updateNickName(String nickName) {
        this.nickName = nickName;
        update();
    }
    public void updateEmail(String email) {
        this.email = email;
        update();
    }
    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        update();
    }
    public void updateProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
        update();
    }
    public void updateStatus(Status status) {
        this.status = status;
        update();
    }


    @Override
    public String toString() {
        return "유저 UUID : " + id
                + "\n 생성 시간 : " + createdAt + ", 수정한 시간 : " + updatedAt
                + "\n 유저 이름 : " + name + ", 유저 닉네임 : " + nickName
                + "\n 유저 이메일 : " + email + ", 유저 전화번호 : " + phoneNumber
                + "\n 프로필 사진 URL : " + profileImageURL + ", 유저 상태 : " + status;
    }
}