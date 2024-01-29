package br.com.luis.partiu.infra.exceptions;

public record ErrorResponse(Long timestamp, Integer status, String error, String message, String path) {
}
