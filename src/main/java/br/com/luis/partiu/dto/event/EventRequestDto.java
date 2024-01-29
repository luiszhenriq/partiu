package br.com.luis.partiu.dto.event;


import br.com.luis.partiu.dto.address.AddressDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventRequestDto(
        @NotBlank(message = "Este campo é obrigatório")
        String title,
        String description,
        @NotBlank(message = "Este campo é obrigatório")
        String coverUrl,
        @NotNull(message = "Este campo não pode ser nulo")
        LocalDateTime startAt,
        @NotNull(message = "Este campo não pode ser nulo")
        LocalDateTime endsIn,
        @NotNull(message = "Este campo não pode ser nulo")
        Integer fee,
        @NotNull(message = "Este campo não pode ser nulo")
        UUID authorId,
        @NotNull(message = "Este campo não pode ser nulo")
        UUID localeId,
        @NotNull(message = "Este campo não pode ser nulo")
        UUID categoryId,

        @NotNull(message = "Este campo não pode ser nulo")
        AddressDto address
        ) {
}
