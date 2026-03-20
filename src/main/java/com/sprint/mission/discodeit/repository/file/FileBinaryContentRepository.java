package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class FileBinaryContentRepository implements BinaryContentRepository {
    private final Map<UUID, BinaryContent> binaryContents = new HashMap<>();

    public FileBinaryContentRepository() {
        load();
    }

    // 저장 메서드 save(직렬화)
    public void save() {
        try (FileOutputStream fos = new FileOutputStream("binaryContents.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(binaryContents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 불러오기 메서드 load(역직렬화)
    public void load() {
        try (FileInputStream fis = new FileInputStream("binaryContents.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Map<UUID, BinaryContent> loadBinaryContents = (Map<UUID, BinaryContent>) ois.readObject();
            binaryContents.clear(); // 한 번 비우고
            binaryContents.putAll(loadBinaryContents); // 불러온다.(기존에 있던 데이터까지 같이 로드될 수 있기 때문에)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void insert(BinaryContent binaryContent) {
        binaryContents.put(binaryContent.getId(), binaryContent);
        save();
    }

    @Override
    public BinaryContent findById(UUID id) {
        return binaryContents.get(id);
    }

    @Override
    public void delete(UUID id) {
        binaryContents.remove(id);
        save();
    }
}
