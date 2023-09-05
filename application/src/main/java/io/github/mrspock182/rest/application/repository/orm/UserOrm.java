package io.github.mrspock182.rest.application.repository.orm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public record UserOrm(
        @Id
        String id,
        String name,
        Integer value
) {
}