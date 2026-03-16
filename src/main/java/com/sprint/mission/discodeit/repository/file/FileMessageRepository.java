package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.repository.MessageRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Repository
public class FileMessageRepository implements MessageRepository {
    private final Map<UUID, Message> messages = new HashMap<>();

    public FileMessageRepository() {
        load();
    }

    // 저장 메서드 save(직렬화)
    public void save() {
        try (FileOutputStream fos = new FileOutputStream("messages.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 불러오기 메서드 load(역직렬화)
    public void load() {
        try (FileInputStream fis = new FileInputStream("messages.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Map<UUID, Message> loadChannels = (Map<UUID, Message>) ois.readObject();
            messages.clear(); // 한 번 비우고
            messages.putAll(loadChannels); // 불러온다.(기존에 있던 데이터까지 같이 로드될 수 있기 때문에)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public UUID findByContent(String content) {
        for (Map.Entry<UUID, Message> message : messages.entrySet()) {
            if (message.getValue().getContent().equals(content)) {
                return message.getKey();
            }
        }
        return null;
    }

    @Override
    public void insert(Message message) {
        messages.put(message.getId(), message);
        save();
    }

    @Override
    public Message findById(UUID id) {
        Message message = messages.get(id);
        if (message == null) {
            throw new NoSuchElementException("해당 메시지가 존재하지 않습니다. id : " + id);
        }
        return messages.get(id);
    }

    @Override
    public void update(Message message) {
        messages.put(message.getId(), message);
        save();
    }

    @Override
    public void delete(UUID id) {
        messages.remove(id);
        save();
    }
}
