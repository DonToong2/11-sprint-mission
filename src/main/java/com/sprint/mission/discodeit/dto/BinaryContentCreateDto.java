package com.sprint.mission.discodeit.dto;

public record BinaryContentCreateDto(
        byte[] bytes,
        String fileName,
        String fileType
) {
}
