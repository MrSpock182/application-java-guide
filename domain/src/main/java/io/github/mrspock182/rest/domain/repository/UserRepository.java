package io.github.mrspock182.rest.domain.repository;

import io.github.mrspock182.rest.domain.entity.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    List<User> findAll(Integer page);
}
