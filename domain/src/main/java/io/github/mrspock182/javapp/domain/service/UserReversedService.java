package io.github.mrspock182.javapp.domain.service;

import io.github.mrspock182.javapp.domain.entity.User;
import io.github.mrspock182.javapp.domain.repository.UserRepository;

import java.util.Comparator;
import java.util.List;

public class UserReversedService {
    private final UserRepository userRepository;

    public UserReversedService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> reversedList() {
        return userRepository.findAll(0).stream()
                .sorted(Comparator.comparing(User::value).reversed())
                .toList();
    }
}