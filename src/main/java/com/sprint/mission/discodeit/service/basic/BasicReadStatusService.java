package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.ReadStatusCreateRequest;
import com.sprint.mission.discodeit.dto.ReadStatusUpdateRequest;
import com.sprint.mission.discodeit.entity.ReadStatus;
import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.ReadStatusRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.service.ReadStatusService;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BasicReadStatusService implements ReadStatusService {

  private final ReadStatusRepository readStatusRepository;
  private final UserRepository userRepository;
  private final ChannelRepository channelRepository;

  @Override
  @Transactional
  public ReadStatus create(ReadStatusCreateRequest dto) {
    // 관련된 Channel, User가 존재하지 않으면 예외를 발생.
    if (dto.user() == null || dto.channel() == null) {
      throw new NoSuchElementException("해당 채널 또는 User가 존재하지 않습니다.");
    }

    // 같은 Channel, User와 관련된 객체가 이미 존재하면 예외를 발생
    readStatusRepository.findByUserAndChannel(dto.user(), dto.channel())
        .ifPresent(readStatus -> {
              throw new IllegalArgumentException(
                  "이미 해당 채널의 읽음 상태가 존재합니다. user: " + dto.user() +
                      ", channelId: " + dto.channel());
            }
        );

    ReadStatus readStatus = new ReadStatus(dto.user(), dto.channel(), Instant.now());
    readStatusRepository.save(readStatus);

    return readStatus;
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ReadStatus> find(UUID id) {
    return readStatusRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ReadStatus> findAllByUserId(List<UUID> userId) {
    return readStatusRepository.findAllById(userId);
  }

  @Override
  @Transactional
  public ReadStatus update(UUID id, ReadStatusUpdateRequest dto) {
    ReadStatus readStatus = readStatusRepository.findById(id).orElseThrow();
    readStatus.updateLastReadAt();
    readStatusRepository.save(readStatus);

    return readStatus;
  }

  @Override
  @Transactional
  public void delete(UUID id) {
    readStatusRepository.deleteById(id);
  }
}
