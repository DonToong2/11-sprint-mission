package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public interface ChannelService {
    // 코드 탬플릿에 맞게 create 메서드 수정
    Channel create(Channel.ChannelType channelType, String name, String description);
//    void createChannel(Channel channel);

    void readChannelAll(UUID id);

    void updateChannelName(UUID id, String newName);
    void updateChannelGroup(UUID id, String newGroup);
    void updateChannelMembersAdd(UUID id, String addMember);
    void updateChannelMembersRemove(UUID id, String removeMember);

    void deleteChannel(UUID id);
}
