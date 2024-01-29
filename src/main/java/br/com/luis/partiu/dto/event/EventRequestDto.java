package br.com.luis.partiu.dto.event;


import br.com.luis.partiu.dto.address.AddressDto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventRequestDto(
        String title,
        String description,
        String coverUrl,
        LocalDateTime startAt,
        LocalDateTime endsIn,
        Integer fee,
        UUID authorId,
        UUID localeId,
        UUID categoryId,

        AddressDto address
        ) {
}
