package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.*;

@Repository
public class FileUserRepository implements UserRepository {

    // User들을 담을 Map 생성
    private final Map<UUID, User> users = new HashMap<>(); // 저장소

    public FileUserRepository() {
        load();
    }

    // 저장 메서드 save(직렬화)
    private void save() {
        try (FileOutputStream fos = new FileOutputStream("users.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 불러오기 메서드 load(역직렬화)
    private void load() {
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
        for (Map.Entry<UUID, User> user : users.entrySet()) {
            if (user.getValue().getNickname().equals(nickname)) {
                return user.getKey();
            }
        }
        return null;
    }

    @Override
    public void insert(User user) {
        users.put(user.getId(), user);
        save();
    }


    @Override
    public User findById(UUID id) {
        // containsKey(해당 키가 있는지 조회)와 get(조회)으로 2번 조회(비효율적)
//        if (!users.containsKey(id)) {
//            throw new NoSuchElementException("해당 유저는 존재하지 않습니다. id : " + id);
//        }
//        return users.get(id);

        // get으로 한번에 조회
        User user = users.get(id);
        if (user == null) {
            throw new NoSuchElementException("해당 유저는 존재하지 않습니다. id : " + id);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return this.users.values().stream().toList();
    }

    @Override
    public void update(User user) {
        users.put(user.getId(), user);
        save();
    }

    @Override
    public void delete(UUID id) {
        users.remove(id);
        save();
    }
}
