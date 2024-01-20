package br.com.luis.partiu.dto.event;


import br.com.luis.partiu.models.User;

import java.util.UUID;

public record EventResponseDto(
        UUID id,
        String title,
        String description,
        String coverUrl,
        String startAt,
        String endsIn,
        Integer fee,
        User author
       ) {
}
