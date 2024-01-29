package br.com.luis.partiu.dto.address;


import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        @NotBlank(message = "Este campo é obrigatório")
        String street,
        @NotBlank(message = "Este campo é obrigatório")
        String neighborhood,
        @NotBlank(message = "Este campo é obrigatório")
        String zipCode,
        @NotBlank(message = "Este campo é obrigatório")
        String ref) {
}
