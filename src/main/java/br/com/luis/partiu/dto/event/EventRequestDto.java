package br.com.luis.partiu.dto.event;

import java.time.LocalDateTime;

public record EventRequestDto(String title, String description, String coverUrl, LocalDateTime startAt, LocalDateTime endsIn, Integer fee) {
}
