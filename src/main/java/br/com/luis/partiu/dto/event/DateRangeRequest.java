package br.com.luis.partiu.dto.event;

import java.time.LocalDateTime;

public record DateRangeRequest(LocalDateTime startAt, LocalDateTime endsIn) {
}
