package io.github.mrspock182.rest.application.integration;

import io.github.mrspock182.rest.domain.entity.User;
import io.github.mrspock182.rest.domain.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryMongodbTests extends MongodbTestContainerConfig {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void saveItemTest() {
        User orm = new User("1", "User-1", 1);
        User save = userRepository.save(orm);
        assertEquals(new User("1", "User-1", 1), save);
    }

    @Test
    @Order(2)
    void findItemTest() {
        List<User> all = userRepository.findAll(0);
        assertEquals(List.of(new User("1", "User-1", 1)), all);
    }
}