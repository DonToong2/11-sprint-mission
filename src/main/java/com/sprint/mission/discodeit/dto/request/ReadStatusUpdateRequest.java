package com.sprint.mission.discodeit.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public record ReadStatusUpdateRequest(
    @NotNull(message = "마지막으로 읽은 시간은 필수입니다.")
    Instant newLastReadAt,

// boolean이 원시타입이기 때문에 null이 될 수 없음
//    @NotNull(message = "알림 여부는 필수입니다.")
    boolean newNotificationEnabled
) {

}
