package io.github.mrspock182.rest.application.repository.adapter;

import io.github.mrspock182.rest.domain.entity.User;
import io.github.mrspock182.rest.application.repository.orm.UserOrm;

public class UserRepositoryAdapter {
    public static User cast(final UserOrm orm) {
        return new User(orm.id(), orm.name(), orm.value());
    }

    public static UserOrm cast(final User dto) {
        return new UserOrm(dto.id(), dto.name(), dto.value());
    }
}
