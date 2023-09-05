package io.github.mrspock182.javapp.springrest.repository;

import io.github.mrspock182.javapp.springrest.repository.adapter.UserRepositoryAdapter;
import io.github.mrspock182.javapp.springrest.repository.client.UserRepositoryWithMongodb;
import io.github.mrspock182.javapp.springrest.repository.orm.UserOrm;
import io.github.mrspock182.javapp.domain.entity.User;
import io.github.mrspock182.javapp.domain.exception.ApplicationErrorException;
import io.github.mrspock182.javapp.domain.exception.NotFoundException;
import io.github.mrspock182.javapp.domain.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryWithMongodb userRepository;

    public UserRepositoryImpl(UserRepositoryWithMongodb userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(final User user) {
        try {
            UserOrm orm = UserRepositoryAdapter.cast(user);
            return UserRepositoryAdapter.cast(userRepository.save(orm));
        } catch (Exception ex) {
            throw new ApplicationErrorException(ex);
        }
    }

    @Override
    public List<User> findAll(final Integer page) {
        try {
            return userRepository.findAll(PageRequest.of(page, 50))
                    .stream()
                    .map(UserRepositoryAdapter::cast)
                    .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                        if (result.isEmpty()) {
                            throw new NotFoundException("Registers not found");
                        }
                        return result;
                    }));
        } catch (NotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ApplicationErrorException(ex);
        }
    }
}