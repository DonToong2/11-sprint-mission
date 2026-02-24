package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;

import java.util.UUID;

public interface ChannelService {
    void createChannel(Channel channel);

    void readChannelName(UUID id);
    void readChannelGroup(UUID id);
    void readChannelMembers(UUID id);
    void readChannelAll(UUID id);

    void updateChannelName(UUID id, String newName);
    void updateChannelGroup(UUID id, String newGroup);
    void updateChannelMembersAdd(UUID id, String addMember);
    void updateChannelMembersRemove(UUID id, String removeMember);

    void deleteChannel(UUID id);
}
