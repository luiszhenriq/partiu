package br.com.luis.partiu.dto.event;


import java.time.LocalDateTime;
import java.util.UUID;

public record EventRequestDto(
        String title,
        String description,
        String coverUrl,
        LocalDateTime startAt,
        LocalDateTime endsIn,
        Integer fee,
        UUID authorId
        ) {
}
