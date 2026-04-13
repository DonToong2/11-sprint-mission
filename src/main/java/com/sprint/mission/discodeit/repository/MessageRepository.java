package com.sprint.mission.discodeit.repository;

import com.sprint.mission.discodeit.entity.Message;
import java.util.List;
import java.util.UUID;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, UUID> {

  Slice<Message> findByChannelIdOrderByCreatedAtDesc(UUID channelId, Pageable pageable);
  // Pageable, 몇 번째 페이지(page)의 몇 개의 데이터(size)를 어떻게(sort, 이름순 최신순 등으로) 가져올지
//    public Pageable(int page, int size, List<String> sort) {
//    this.page = page;
//    this.size = size;
//    this.sort = sort;
//  }

  List<Message> findAllByChannelId(UUID id);

  void deleteAllByChannelId(UUID id);
}
