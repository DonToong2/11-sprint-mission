package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasicChannelService implements ChannelService {
    private final ChannelRepository channelRepository;

    public BasicChannelService(ChannelRepository channelRepository) { this.channelRepository = channelRepository; }

    // Create
    @Override
    public Channel create(Channel.ChannelType channelType, String name, String description) {
        Channel channel = new Channel(channelType, name, description);
        channelRepository.insertChannel(channel);
        System.out.println("채널을 생성하였습니다.");
        System.out.println();

        return channel;
    }

    // Read
    @Override
    public void readChannelAll(UUID id) {
        // NPE 방지
        if (!channelRepository.isExistsChannel(id)) { System.out.println("해당 채널은 존재하지 않습니다."); }
        else {
            Channel channel = channelRepository.findChannel(id);
            System.out.println("=====채널 정보=====\n" + channel);
        }
        System.out.println();
    }

    // Update
    @Override
    public void updateChannelName(UUID id, String newName) {
        // NPE 방지
        if (!channelRepository.isExistsChannel(id)) { System.out.println("해당 채널은 존재하지 않습니다."); }
        else {
            Channel channel = channelRepository.findChannel(id);
            System.out.println("수정 전 채널 이름 : " + channel.getName());
            channel.updateName(newName);
            System.out.println("수정 후 채널 이름 : " + channel.getName());
            channelRepository.updateChannel(channel);
        }
        System.out.println();
    }

    @Override
    public void updateChannelGroup(UUID id, String newGroup) {
        // NPE 방지
        if (!channelRepository.isExistsChannel(id)) { System.out.println("해당 채널은 존재하지 않습니다."); }
        else {
            Channel channel = channelRepository.findChannel(id);
            System.out.println("수정 전 속한 채널 그룹 : " + channel.getGroup());
            channel.updateGroup(newGroup);
            System.out.println("수정 후 속한 채널 그룹 : " + channel.getGroup());
            channelRepository.updateChannel(channel);
        }
        System.out.println();
    }

    @Override
    public void updateChannelMembersAdd(UUID id, String addMember) {
        // NPE 방지
        if (!channelRepository.isExistsChannel(id)) { System.out.println("해당 채널은 존재하지 않습니다."); }
        else {
            Channel channel = channelRepository.findChannel(id);
            List<String> members = new ArrayList<>(channel.getMembers());
            System.out.println("수정 전 채널 멤버 : " + channel.getMembers());
            members.add(addMember);
            channel.updateMember(members);
            System.out.println("수정 후 채널 멤버 : " + channel.getMembers());
            channelRepository.updateChannel(channel);
        }
        System.out.println();
    }

    @Override
    public void updateChannelMembersRemove(UUID id, String removeMember) {
        // NPE 방지
        if (!channelRepository.isExistsChannel(id)) { System.out.println("해당 채널은 존재하지 않습니다."); }
        else {
            Channel channel = channelRepository.findChannel(id);
            List<String> members = new ArrayList<>(channel.getMembers());
            System.out.println("수정 전 채널 멤버 : " + channel.getMembers());
            members.remove(removeMember);
            channel.updateMember(members);
            System.out.println("수정 후 채널 멤버 : " + channel.getMembers());
            channelRepository.updateChannel(channel);
        }
        System.out.println();
    }

    // Delete
    @Override
    public void deleteChannel(UUID id) {
        // NPE 방지
        if (!channelRepository.isExistsChannel(id)) { System.out.println("해당 채널은 존재하지 않습니다."); }
        else {
            Channel channel = channelRepository.findChannel(id);
            System.out.println("채널" + channel.getName() + "이(가) 삭제되었습니다.");
            channelRepository.deleteChannel(id);
        }
        System.out.println();
    }
}
