package com.sprint.mission.discodeit.sse.controller;

import com.sprint.mission.discodeit.security.auth.DiscodeitUserDetails;
import com.sprint.mission.discodeit.sse.service.SseService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class SseController {

  private final SseService sseService;

  @GetMapping("/api/sse")
  public SseEmitter connect(
      Authentication authentication,
      @RequestHeader(value = "Last-Event-ID", required = false) UUID lastEventId // SseMessage ID
  ) {

    UUID receiverId = UUID.fromString(
        (
            (DiscodeitUserDetails) authentication.getPrincipal() // Object(getPrincipal) → DiscodeitUserDetails
        ).getId()
    );
    return sseService.connect(receiverId, lastEventId);
  }

}
