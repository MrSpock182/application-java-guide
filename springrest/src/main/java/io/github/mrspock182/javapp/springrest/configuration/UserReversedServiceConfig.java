package io.github.mrspock182.javapp.springrest.configuration;

import io.github.mrspock182.javapp.domain.repository.UserRepository;
import io.github.mrspock182.javapp.domain.service.UserReversedService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserReversedServiceConfig {

    @Bean
    public UserReversedService userReversedService(final UserRepository userRepository) {
        return new UserReversedService(userRepository);
    }

}
