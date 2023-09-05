package io.github.mrspock182.rest.application.resource.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserRequest(
        @NotEmpty(message = "User name can't be empty")
        String name,
        Integer value
) {
}
