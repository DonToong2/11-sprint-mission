package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;

import java.util.UUID;

public interface ChannelService {
    // 코드 탬플릿에 맞게 create 메서드 수정
    Channel create(Channel.ChannelType channelType, String name, String description);
//    void createChannel(Channel channel);

    Channel readAll(UUID id);

    Channel updateName(UUID id, String newName);

    Channel updateGroup(UUID id, String newGroup);

    Channel updateMembersAdd(UUID id, String addMember);

    Channel updateMembersRemove(UUID id, String removeMember);

    void delete(UUID id);
}
