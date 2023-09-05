package io.github.mrspock182.javapp.springrest.repository.adapter;

import io.github.mrspock182.javapp.domain.entity.User;
import io.github.mrspock182.javapp.springrest.repository.orm.UserOrm;

public class UserRepositoryAdapter {
    public static User cast(final UserOrm orm) {
        return new User(orm.id(), orm.name(), orm.value());
    }

    public static UserOrm cast(final User dto) {
        return new UserOrm(dto.id(), dto.name(), dto.value());
    }
}
