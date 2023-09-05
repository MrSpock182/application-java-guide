package io.github.mrspock182.rest.application.repository;

import io.github.mrspock182.rest.application.repository.adapter.UserRepositoryAdapter;
import io.github.mrspock182.rest.application.repository.client.UserRepositoryWithMongodb;
import io.github.mrspock182.rest.application.repository.orm.UserOrm;
import io.github.mrspock182.rest.domain.entity.User;
import io.github.mrspock182.rest.domain.exception.InternalServerError;
import io.github.mrspock182.rest.domain.exception.NotFound;
import io.github.mrspock182.rest.domain.repository.UserRepository;
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
            throw new InternalServerError(ex);
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
                            throw new NotFound("Registers not found");
                        }
                        return result;
                    }));
        } catch (NotFound ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
    }
}