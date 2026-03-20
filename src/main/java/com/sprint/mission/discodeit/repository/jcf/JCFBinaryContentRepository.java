package com.sprint.mission.discodeit.repository.jcf;

import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JCFBinaryContentRepository implements BinaryContentRepository {
    private final Map<UUID, BinaryContent> binaryContents = new HashMap<>();

    @Override
    public void insert(BinaryContent binaryContent) {
        binaryContents.put(binaryContent.getId(), binaryContent);
    }

    @Override
    public BinaryContent findById(UUID id) {
        return binaryContents.get(id);
    }

    @Override
    public void delete(UUID id) {
        binaryContents.remove(id);
    }
}
