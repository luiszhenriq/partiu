package br.com.luis.partiu.dto.user;

import br.com.luis.partiu.models.Gender;

public record UserRegisterDto(String name, String email, String password, String avatarUrl, Gender gender) {
}
