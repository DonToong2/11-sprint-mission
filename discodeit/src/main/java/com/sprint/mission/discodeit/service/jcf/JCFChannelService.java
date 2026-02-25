package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.*;

public class JCFChannelService implements ChannelService {
    private final Map<UUID, Channel> channels = new HashMap<>();

    // Create
    @Override
    public void createChannel(Channel channel) {
        channels.put(channel.getId(), channel);
        System.out.println("채널을 생성하였습니다.");
        System.out.println();
    }

    // Read
    @Override
    public void readChannelAll(UUID id) {
        Channel channel = channels.get(id);
        System.out.println("=====채널 정보=====\n" + channel);
        System.out.println();
    }

    // Update
    @Override
    public void updateChannelName(UUID id, String newName) {
        Channel channel = channels.get(id);
        System.out.println("수정 전 채널 이름 : " + channel.getName());
        channel.updateName(newName);
        System.out.println("수정 후 채널 이름 : " + channel.getName());
        System.out.println();
    }

    @Override
    public void updateChannelGroup(UUID id, String newGroup) {
        Channel channel = channels.get(id);
        System.out.println("수정 전 속한 채널 그룹 : " + channel.getGroup());
        channel.updateGroup(newGroup);
        System.out.println("수정 후 속한 채널 그룹 : " + channel.getGroup());
        System.out.println();
    }

    @Override
    public void updateChannelMembersAdd(UUID id, String addMember) {
        Channel channel = channels.get(id);
        List<String> members = new ArrayList<>(channel.getMembers());
        System.out.println("수정 전 채널 멤버 : " + channel.getMembers());
        members.add(addMember);
        channel.updateMember(members);
        System.out.println("수정 후 채널 멤버 : " + channel.getMembers());
        System.out.println();
    }

    @Override
    public void updateChannelMembersRemove(UUID id, String removeMember) {
        Channel channel = channels.get(id);
        List<String> members = new ArrayList<>(channel.getMembers());
        System.out.println("수정 전 채널 멤버 : " + channel.getMembers());
        members.remove(removeMember);
        channel.updateMember(members);
        System.out.println("수정 후 채널 멤버 : " + channel.getMembers());
        System.out.println();
    }

    // Delete
    @Override
    public void deleteChannel(UUID id) {
        Channel channel = channels.get(id);
        channels.remove(id);
        if (channel == null) {
            System.out.println("해당 채널은 존재하지 않습니다.");
        }
        else {
            System.out.println("채널" + channel.getName() + "이(가) 삭제되었습니다.");
        }
        System.out.println();
    }
}
