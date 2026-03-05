package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileChannelRepository implements ChannelRepository {
    private final Map<UUID, Channel> channels = new HashMap<>();
    public FileChannelRepository() {
        load();
    }

    // 저장 메서드 save(직렬화)
    public void save() {
        try (FileOutputStream fos = new FileOutputStream("channels.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(channels);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 불러오기 메서드 load(역직렬화)
    public void load() {
        try (FileInputStream fis = new FileInputStream("channels.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Map<UUID, Channel> loadChannels = (Map<UUID, Channel>) ois.readObject();
            channels.clear(); // 한 번 비우고
            channels.putAll(loadChannels); // 불러온다.(기존에 있던 데이터까지 같이 로드될 수 있기 때문에)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 채널명으로 UUID 호출
    public UUID findIdByName(String name) {
        for (Map.Entry<UUID, Channel> channel : channels.entrySet()) {
            if (channel.getValue().getName().equals(name)) {
                return channel.getKey();
            }
        }
        return null;
    }

    @Override
    public void insertChannel(Channel channel) {
        channels.put(channel.getId(), channel);
        save();
    }

    @Override
    public boolean isExistsChannel(UUID id) {
        return channels.containsKey(id);
    }

    @Override
    public Channel findChannel(UUID id) { return channels.get(id); }

    @Override
    public void updateChannel(Channel channel) {
        channels.put(channel.getId(), channel);
        save();
    }

    @Override
    public void deleteChannel(UUID id) {
        channels.remove(id);
        save();
    }
}
