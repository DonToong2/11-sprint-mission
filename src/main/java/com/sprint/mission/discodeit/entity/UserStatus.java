package com.sprint.mission.discodeit.entity;

public enum UserStatus {
    ONLINE("온라인"), AWAY("자리비움"), DO_NOT_DISTURB("방해 금지"), OFFLINE("오프라인");

    private final String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}