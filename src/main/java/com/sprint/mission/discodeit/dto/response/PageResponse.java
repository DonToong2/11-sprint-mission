package com.sprint.mission.discodeit.dto.response;

import java.util.List;

// 페이징 처리 BinaryContentDto, UserDto 등 어떤 타입이 들어올지 모르기 때문에(= 어떤 타입이든 가능하도록)
public record PageResponse<T>(
    List<T> content, // 실제 데이터
    int number, // 페이지 번호
    int size, // 페이지 크기
    boolean hasNext,
    Long totalElements // nullable
) {

}
