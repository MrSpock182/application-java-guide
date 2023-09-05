package io.github.mrspock182.javapp.springrest.resource;

import io.github.mrspock182.javapp.domain.entity.User;
import io.github.mrspock182.javapp.domain.repository.UserRepository;
import io.github.mrspock182.javapp.domain.service.UserReversedService;
import io.github.mrspock182.javapp.springrest.resource.adapter.UserResourceAdapter;
import io.github.mrspock182.javapp.springrest.resource.dto.UserRequest;
import io.github.mrspock182.javapp.springrest.resource.dto.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public record UserResource(
        UserRepository userRepository,
        UserReversedService userReversedService
) {
    private static final Logger log = LoggerFactory.getLogger(UserResource.class);

    @GetMapping("/names")
    public List<UserResponse> getNames() {
        return userReversedService.reversedList()
                .stream()
                .map(UserResourceAdapter::cast)
                .toList();
    }

    @PostMapping("/names")
    public UserResponse setNames(@RequestBody final UserRequest request) {
        User user = userRepository.save(UserResourceAdapter.cast(request));
        return UserResourceAdapter.cast(user);
    }
}