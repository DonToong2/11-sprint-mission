package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.service.ChannelService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasicChannelService implements ChannelService {
    private final ChannelRepository channelRepository;

    public BasicChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    // Create
    @Override
    public Channel create(Channel.ChannelType channelType, String name, String description) {
        Channel channel = Channel.create(channelType, name, description);
        channelRepository.insert(channel);
        System.out.println("채널을 생성하였습니다.");
        System.out.println();

        return channel;
    }

    // Read
    @Override
    public Channel readAll(UUID id) {
        Channel channel = channelRepository.findById(id);
        System.out.println("=====채널 정보=====\n" + channel);
        System.out.println();

        return channel;
    }

    // Update
    @Override
    public Channel updateName(UUID id, String newName) {
        Channel channel = channelRepository.findById(id);
        System.out.println("수정 전 채널 이름 : " + channel.getName());
        channel.updateName(newName);
        System.out.println("수정 후 채널 이름 : " + channel.getName());
        channelRepository.update(channel);
        System.out.println();

        return channel;
    }

    @Override
    public Channel updateGroup(UUID id, String newGroup) {
        Channel channel = channelRepository.findById(id);
        System.out.println("수정 전 속한 채널 그룹 : " + channel.getGroup());
        channel.updateGroup(newGroup);
        System.out.println("수정 후 속한 채널 그룹 : " + channel.getGroup());
        channelRepository.update(channel);
        System.out.println();

        return channel;
    }

    @Override
    public Channel updateMembersAdd(UUID id, String addMember) {
        Channel channel = channelRepository.findById(id);
        List<String> members = new ArrayList<>(channel.getMembers());
        System.out.println("수정 전 채널 멤버 : " + channel.getMembers());
        members.add(addMember);
        channel.updateMember(members);
        System.out.println("수정 후 채널 멤버 : " + channel.getMembers());
        channelRepository.update(channel);
        System.out.println();

        return channel;
    }

    @Override
    public Channel updateMembersRemove(UUID id, String removeMember) {
        Channel channel = channelRepository.findById(id);
        List<String> members = new ArrayList<>(channel.getMembers());
        System.out.println("수정 전 채널 멤버 : " + channel.getMembers());
        members.remove(removeMember);
        channel.updateMember(members);
        System.out.println("수정 후 채널 멤버 : " + channel.getMembers());
        channelRepository.update(channel);
        System.out.println();

        return channel;
    }

    // Delete
    @Override
    public void delete(UUID id) {
        Channel channel = channelRepository.findById(id);
        System.out.println("채널" + channel.getName() + "이(가) 삭제되었습니다.");
        channelRepository.delete(id);
        System.out.println();
    }
}
