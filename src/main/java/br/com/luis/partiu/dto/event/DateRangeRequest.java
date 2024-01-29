package br.com.luis.partiu.dto.event;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DateRangeRequest(
        @NotBlank(message = "Este campo é obrigatório")
        LocalDateTime startAt,
        @NotBlank(message = "Este campo é obrigatório")
        LocalDateTime endsIn) {
}
