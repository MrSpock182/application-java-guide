package io.github.mrspock182.rest.application.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.mrspock182.rest.application.resource.advice.HandlerExceptionAdvice;
import io.github.mrspock182.rest.application.resource.dto.UserRequest;
import io.github.mrspock182.rest.domain.entity.User;
import io.github.mrspock182.rest.domain.exception.ApplicationErrorException;
import io.github.mrspock182.rest.domain.exception.NotFoundException;
import io.github.mrspock182.rest.domain.repository.UserRepository;
import io.github.mrspock182.rest.domain.service.UserReversedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("local")
class UserResourceTests {
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .findAndAddModules()
            .build();
    @Autowired
    private UserResource userResource;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserReversedService userReversedService;

    private User user() {
        return new User("1", "name-1", 1);
    }

    @Test
    void whenSaveIsSuccessfulFlowStatus200Test() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(userResource)
                .setControllerAdvice(new HandlerExceptionAdvice())
                .build();
        when(userRepository.save(any(User.class))).thenReturn(user());
        mockMvc.perform(post("/names")
                        .content(objectMapper.writeValueAsString(new UserRequest("name-1", 1)))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void whenSaveStatus400ValidateNameRequestBlankTest() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(userResource)
                .setControllerAdvice(new HandlerExceptionAdvice())
                .build();
        mockMvc.perform(post("/names")
                        .content(objectMapper.writeValueAsString(new UserRequest("", 1)))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenSaveStatus400ValidateNameRequestNullTest() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(userResource)
                .setControllerAdvice(new HandlerExceptionAdvice())
                .build();
        mockMvc.perform(post("/names")
                        .content(objectMapper.writeValueAsString(new UserRequest(null, 1)))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenSaveStatus400CharacterMoreThan45Test() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(userResource)
                .setControllerAdvice(new HandlerExceptionAdvice())
                .build();
        mockMvc.perform(post("/names")
                        .content(objectMapper.writeValueAsString(new UserRequest(
                                "tetstetatatatstatstatststetehehrhahshdhahdkfertkkw", 1)))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenSaveStatus400ValueMoreThan99Test() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(userResource)
                .setControllerAdvice(new HandlerExceptionAdvice())
                .build();
        mockMvc.perform(post("/names")
                        .content(objectMapper.writeValueAsString(new UserRequest("test-1", 100)))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenSaveStatus500ErrorToDatabaseTest() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(userResource)
                .setControllerAdvice(new HandlerExceptionAdvice())
                .build();
        when(userRepository.save(any(User.class))).thenThrow(new ApplicationErrorException());
        mockMvc.perform(post("/names")
                        .content(objectMapper.writeValueAsString(new UserRequest("name-1", 1)))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void whenFindAllStatus200SuccessfulFlow200Test() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(userResource)
                .setControllerAdvice(new HandlerExceptionAdvice())
                .build();
        when(userReversedService.reversedList()).thenReturn(List.of(user()));
        mockMvc.perform(get("/names")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(userReversedService).reversedList();
    }

    @Test
    void whenFindAllStatus404ListIsEmptyTest() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(userResource)
                .setControllerAdvice(new HandlerExceptionAdvice())
                .build();
        when(userReversedService.reversedList()).thenThrow(new NotFoundException());
        mockMvc.perform(get("/names")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(userReversedService).reversedList();
    }

    @Test
    void whenFindAllStatus500DatabaseErrorTest() throws Exception {
        final MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(userResource)
                .setControllerAdvice(new HandlerExceptionAdvice())
                .build();
        when(userReversedService.reversedList()).thenThrow(new ApplicationErrorException());
        mockMvc.perform(get("/names")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
        verify(userReversedService).reversedList();
    }
}