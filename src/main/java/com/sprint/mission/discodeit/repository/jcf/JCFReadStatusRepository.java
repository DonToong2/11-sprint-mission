package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

public class JCFReadStatusRepository implements ReadStatusRepository {
    private final Map<UUID, ReadStatus> readStatuses = new HashMap<>();

    @Override
    public void insert(ReadStatus readStatus) {
        readStatuses.put(readStatus.getId(), readStatus);
    }

    @Override
    public ReadStatus findById(UUID id) {
        ReadStatus readStatus = readStatuses.get(id);
        if (readStatus == null) {
            throw new NoSuchElementException("해당 ReadStatus가 존재하지 않습니다. id : " + id);
        }

        return readStatus;
    }

    @Override
    public List<ReadStatus> findByUserId(UUID userId) {
        return readStatuses.values().stream()
                .filter(readStatus -> readStatus.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadStatus> findByChannelId(UUID channelId) {
        return readStatuses.values().stream()
                .filter(readStatus -> readStatus.getChannelId().equals(channelId))
                .collect(Collectors.toList());
    }

    @Override
    public void update(ReadStatus readStatus) {
        readStatuses.put(readStatus.getId(), readStatus);
    }

    @Override
    public void delete(UUID id) {
        readStatuses.remove(id);
    }

    @Override
    public void deleteAllByChannelId(UUID channelId) {
        readStatuses.values().removeIf(readStatus -> readStatus.getChannelId().equals(channelId));
    }
}
