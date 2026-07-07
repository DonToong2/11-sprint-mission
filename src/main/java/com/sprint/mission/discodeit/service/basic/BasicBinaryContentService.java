package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.request.BinaryContentCreateRequest;
import com.sprint.mission.discodeit.dto.response.BinaryContentDto;
import com.sprint.mission.discodeit.entity.BinaryContent;
import com.sprint.mission.discodeit.event.BinaryContentCreatedEvent;
import com.sprint.mission.discodeit.exception.binarycontent.BinaryContentNotFoundException;
import com.sprint.mission.discodeit.mapper.BinaryContentMapper;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.service.BinaryContentService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BasicBinaryContentService implements BinaryContentService {

  private final BinaryContentRepository binaryContentRepository;
  private final BinaryContentMapper binaryContentMapper;

  private final ApplicationEventPublisher eventPublisher;

  @Override
  @Transactional
  public BinaryContentDto create(BinaryContentCreateRequest dto) {
    BinaryContent binaryContent = dto.toBinaryContent();
    binaryContentRepository.save(binaryContent);

    // 기존 BinaryContentStorage.put 메서드를 이벤트로 처리
    // 이벤트 리스너에서 AFTER_COMMIT 옵션으로 트랜잭션 커밋 후 전달받은 이벤트를 처리하기 때문에 DB 커넥션 점유 시간 감소
    eventPublisher.publishEvent(new BinaryContentCreatedEvent(binaryContent.getId(), dto.bytes()));

    return binaryContentMapper.toDto(binaryContent);
  }

  @Override
  @Transactional(readOnly = true)
  public BinaryContentDto find(UUID id) {
    BinaryContent binaryContent = binaryContentRepository.findById(id).orElseThrow(
        () -> new BinaryContentNotFoundException(id)
    );
    return binaryContentMapper.toDto(binaryContent);
  }

  @Override
  @Transactional(readOnly = true)
  public List<BinaryContentDto> findAllByIdIn(List<UUID> ids) {
    return binaryContentRepository.findAllById(ids).stream()
        .map(binaryContentMapper::toDto).toList();
  }

  @Override
  @Transactional
  public void delete(UUID id) {
    if (!binaryContentRepository.existsById(id)) {
      throw new BinaryContentNotFoundException(id);
    }
    binaryContentRepository.deleteById(id);
  }
}