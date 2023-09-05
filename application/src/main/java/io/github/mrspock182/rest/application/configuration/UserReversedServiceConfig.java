package io.github.mrspock182.rest.application.configuration;

import io.github.mrspock182.rest.domain.repository.UserRepository;
import io.github.mrspock182.rest.domain.service.UserReversedService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserReversedServiceConfig {

    @Bean
    public UserReversedService userReversedService(final UserRepository userRepository) {
        return new UserReversedService(userRepository);
    }

}
