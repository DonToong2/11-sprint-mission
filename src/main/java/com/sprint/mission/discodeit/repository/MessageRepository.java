package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Message;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, UUID> {

  Slice<Message> findByChannelIdOrderByCreatedAtDesc(UUID channelId, Pageable pageable);
  // Pageable, 몇 번째 페이지(page)의 몇 개의 데이터(size)로 나타낼 지 ?

  List<Message> findAllByChannelId(UUID id);

  void deleteAllByChannelId(UUID id);
}
