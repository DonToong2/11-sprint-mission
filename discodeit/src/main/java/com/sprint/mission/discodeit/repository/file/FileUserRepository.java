package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FileUserRepository implements UserRepository {

    // User들을 담을 Map 생성
    private final Map<UUID, User> users = new HashMap<>(); // 저장소

    // 저장 메서드 save(직렬화)
    public void save() {
        try (FileOutputStream fos = new FileOutputStream("users.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(users);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 불러오기 메서드 load(역직렬화)
    public void load() {
        try (FileInputStream fis = new FileInputStream("users.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Map<UUID, User> loadUsers = (Map<UUID, User>) ois.readObject();
            users.clear(); // 한 번 비우고
            users.putAll(loadUsers); // 불러온다.(기존에 있던 데이터까지 같이 로드될 수 있기 때문에)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 닉네임으로 UUID 호출
    public UUID findIdByNickname(String nickname) {
        load();
        for (Map.Entry<UUID, User> user : users.entrySet()) {
            if (user.getValue().getNickname().equals(nickname)) {
                return user.getKey();
            }
        }
        return null;
    }

    @Override
    public void insertUser(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public boolean isExistsUser(UUID id) {
        return users.containsKey(id);
    }

    @Override
    public User findUser(UUID id) {
        return users.get(id);
    }

    @Override
    public void deleteUser(UUID id) {
        users.remove(id);
    }
}
