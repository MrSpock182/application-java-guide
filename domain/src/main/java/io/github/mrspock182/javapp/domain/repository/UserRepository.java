package io.github.mrspock182.javapp.domain.repository;

import io.github.mrspock182.javapp.domain.entity.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    List<User> findAll(Integer page);
}
