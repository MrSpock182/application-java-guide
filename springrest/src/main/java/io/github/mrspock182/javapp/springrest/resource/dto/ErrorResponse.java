package io.github.mrspock182.javapp.springrest.resource.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponse(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime date,
        String path,
        Integer status,
        String error,
        String message
) {
}