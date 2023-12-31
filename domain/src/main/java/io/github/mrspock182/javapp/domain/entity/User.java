package io.github.mrspock182.javapp.domain.entity;

import io.github.mrspock182.javapp.domain.exception.InvalidAttributeException;

public record User(String id, String name, Integer value) {
    public User {
        validate(name, value);
    }

    private void validate(final String name, final Integer value) {
        if (name == null || name.isEmpty()) {
            throw new InvalidAttributeException("Name can't be empty");
        }

        if (name.length() > 45) {
            throw new InvalidAttributeException("Name can't be more than 45 characters");
        }

        if (value > 99) {
            throw new InvalidAttributeException("Value can't be more than 99");
        }
    }
}