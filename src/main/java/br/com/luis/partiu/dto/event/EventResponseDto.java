package br.com.luis.partiu.dto.event;

import java.util.UUID;

public record EventResponseDto(UUID id, String title, String description, String coverUrl, String startAt, String endsIn, Integer fee) {
}
