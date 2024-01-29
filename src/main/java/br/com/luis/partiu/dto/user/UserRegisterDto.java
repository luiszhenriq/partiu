package br.com.luis.partiu.dto.user;

import br.com.luis.partiu.models.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegisterDto(
        @NotBlank(message = "Este campo é obrigatório")
        String name,
        @NotBlank(message = "Este campo é obrigatório")
        @Email
        String email,
        @NotBlank(message = "Este campo é obrigatório")
        String password,
        @NotBlank(message = "Este campo é obrigatório")
        String avatarUrl,
        @NotNull(message = "Este campo nao pode ser nulo")
        Gender gender) {
}
