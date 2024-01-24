package br.com.luis.partiu.dto.user;

import br.com.luis.partiu.models.Gender;

import java.util.UUID;

public record UserResponseDto(UUID id, String name, String email, String avatarUrl, Gender gender) {
}
