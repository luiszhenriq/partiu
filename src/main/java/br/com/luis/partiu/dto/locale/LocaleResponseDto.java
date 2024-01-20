package br.com.luis.partiu.dto.locale;

import java.util.UUID;

public record LocaleResponseDto(UUID id, String city, String state) {
}
