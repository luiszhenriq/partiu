package br.com.luis.partiu.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateDto(
        @NotBlank(message = "Este campo é obrigatório")
        String avatarUrl) {
}
