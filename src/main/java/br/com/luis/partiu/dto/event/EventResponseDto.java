package br.com.luis.partiu.dto.event;


import br.com.luis.partiu.dto.user.UserViewDto;
import br.com.luis.partiu.models.Address;
import br.com.luis.partiu.models.Category;
import br.com.luis.partiu.models.Locale;

import java.util.UUID;

public record EventResponseDto(
        UUID id,
        String title,
        String description,
        String coverUrl,
        String startAt,
        String endsIn,
        Integer fee,
        UserViewDto author,
        Locale locale,
        Category category,
        Address address
       ) {
}
