package io.github.mrspock182.javapp.springrest.resource.adapter;

import io.github.mrspock182.javapp.springrest.resource.dto.UserRequest;
import io.github.mrspock182.javapp.springrest.resource.dto.UserResponse;
import io.github.mrspock182.javapp.domain.entity.User;

import java.util.UUID;

public class UserResourceAdapter {
    private UserResourceAdapter() {
    }

    public static UserResponse cast(final User user) {
        return new UserResponse(user.name(), user.value());
    }

    public static User cast(final UserRequest request) {
        return new User(UUID.randomUUID().toString(), request.name(), request.value());
    }
}
