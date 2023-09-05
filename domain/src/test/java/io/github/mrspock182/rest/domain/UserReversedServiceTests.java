package io.github.mrspock182.rest.domain;

import io.github.mrspock182.rest.domain.entity.User;
import io.github.mrspock182.rest.domain.repository.UserRepository;
import io.github.mrspock182.rest.domain.service.UserReversedService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserReversedServiceTests {
    @InjectMocks
    private UserReversedService service;
    @Mock
    private UserRepository userRepository;

    @Test
    void reversedListWhenHaveItemsAtListTest() {
        when(userRepository.findAll(anyInt())).thenReturn(listInOrder());
        List<User> reversedList = service.reversedList();
        assertEquals(listReversed(), reversedList);
        verify(userRepository).findAll(anyInt());
    }

    private List<User> listInOrder() {
        return List.of(new User("1", "name-1", 1),
                new User("2", "name-2", 2),
                new User("3", "name-3", 3));
    }

    private List<User> listReversed() {
        return List.of(new User("3", "name-3", 3),
                new User("2", "name-2", 2),
                new User("1", "name-1", 1));
    }

}