package br.com.luis.partiu.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDto(
        @NotBlank(message = "Este campo é obrigatório")
        @Email
        String email,
        @NotBlank(message = "Este campo é obrigatório")
        String password) {
}
