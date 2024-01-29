package br.com.luis.partiu.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateEventDto(
        @NotBlank(message = "Este campo é obrigatório")
        String title,
        String description,
        @NotBlank(message = "Este campo é obrigatório")
        String coverUrl,
        @NotBlank(message = "Este campo é obrigatório")
        LocalDateTime startAt,
        @NotBlank(message = "Este campo é obrigatório")
        LocalDateTime endsIn,
        @NotNull(message = "Este campon não pode ser nulo")
        Integer fee) {
}
