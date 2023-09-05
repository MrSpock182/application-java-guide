package io.github.mrspock182.rest.application.repository;

import com.mongodb.MongoException;
import io.github.mrspock182.rest.application.MockitoInitTests;
import io.github.mrspock182.rest.application.repository.client.UserRepositoryWithMongodb;
import io.github.mrspock182.rest.application.repository.orm.UserOrm;
import io.github.mrspock182.rest.domain.entity.User;
import io.github.mrspock182.rest.domain.exception.ApplicationErrorException;
import io.github.mrspock182.rest.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserRepositoryImplTest extends MockitoInitTests {
    @InjectMocks
    private UserRepositoryImpl repository;

    @Mock
    private UserRepositoryWithMongodb client;

    private UserOrm userOrm() {
        return new UserOrm("1", "user-1", 1);
    }

    @Override
    public void init() {
    }

    @Test
    void whenIsSuccessfulFlowSaveTest() {
        when(client.save(any(UserOrm.class))).thenReturn(userOrm());
        User user = repository.save(new User("1", "user-1", 1));
        assertEquals(new User("1", "user-1", 1), user);
        verify(client).save(any(UserOrm.class));
    }

    @Test
    void whenIntegrationWithDatabaseIsErrorTest() {
        assertThrows(ApplicationErrorException.class, () -> {
            when(client.save(any(UserOrm.class))).thenThrow(new MongoException(""));
            User user = repository.save(new User("1", "user-1", 1));
            assertEquals(new User("1", "user-1", 1), user);
            verify(client).save(any(UserOrm.class));
        });
    }

    private Page<UserOrm> userPage() {
        return new PageImpl<>(List.of(new UserOrm("1", "user-1", 1)));
    }

    @Test
    void whenIsSuccessfulFlowFindAllTest() {
        when(client.findAll(any(PageRequest.class))).thenReturn(userPage());
        List<User> list = repository.findAll(0);
        assertEquals(List.of(new User("1", "user-1", 1)), list);
        verify(client).findAll(any(PageRequest.class));
    }

    @Test
    void whenIsNotRegisterOnTheListTest() {
        assertThrows(NotFoundException.class, () -> {
            when(client.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(List.of()));
            repository.findAll(0);
            verify(client).findAll(any(PageRequest.class));
        });
    }

    @Test
    void whenIntegrationDatabaseIsErrorTest() {
        assertThrows(ApplicationErrorException.class, () -> {
            when(client.findAll(any(PageRequest.class))).thenThrow(new MongoException(""));
            repository.findAll(0);
            verify(client).findAll(any(PageRequest.class));
        });
    }
}