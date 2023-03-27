package kgkars.spring.modulith.playground.user.internal.controller;

import kgkars.spring.modulith.playground.common.Role;
import kgkars.spring.modulith.playground.common.dto.NewUserDTO;
import kgkars.spring.modulith.playground.user.UserService;
import kgkars.spring.modulith.playground.user.internal.entity.User;
import kgkars.spring.modulith.playground.user.internal.exception.InvalidUserIdFormatException;
import kgkars.spring.modulith.playground.user.internal.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class UserControllerTest {

    @BeforeEach
    void setUp() {
        _user = User.builder()
                .userId(_MOCK_UUID)
                .firstName("John")
                .lastName("Smith")
                .email("test@email.com")
                .password("1234")
                .enabled(true)
                .expired(false)
                .locked(false)
                .role(Role.USER)
                .build();
    }

    @Test
    @DisplayName("Find User with valid User ID")
    void findUserByValidId() throws Exception {

        Mockito.when(_userService.findUserById(_MOCK_UUID))
                .thenReturn(_user);

        _mockMvc.perform(get("/api/v1/user/" + _MOCK_UUID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName")
                        .value(_user.getFirstName()));
    }

    @Test
    @DisplayName("Find User with non-UUID User ID")
    void findUserByNonUUIDUserId() throws Exception {
        Mockito.when(_userService.findUserById(_MOCK_UUID))
                .thenReturn(_user);

        _mockMvc.perform(get("/api/v1/user/abcd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidUserIdFormatException));
    }

    @Test
    @DisplayName("Find User with wrong User ID")
    void findUserByWrongUserId() throws Exception {
        Mockito.when(_userService.findUserById(UUID.fromString("3ce7d9ea-6978-4a8e-9b0a-77b998a1fdb")))
                .thenThrow(new UserNotFoundException("User ID is not correct"));

        _mockMvc.perform(get("/api/v1/user/3ce7d9ea-6978-4a8e-9b0a-77b998a1fdb")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UserNotFoundException));
    }

    @Test
    @DisplayName("Save valid user")
    void saveValidUser() throws Exception {
        NewUserDTO newUser = NewUserDTO.builder()
                .firstName("John")
                .lastName("Smith")
                .email("test@email.com")
                .password("1234")
                .role("USER")
                .build();

        Mockito.when(_userService.create(newUser))
                .thenReturn(_user);

        _mockMvc.perform(post("/api/v1/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"firstName\": \"John\",\n" +
                        "\t\"lastName\": \"Smith\",\n" +
                        "\t\"email\": \"tesdt@email.com\",\n" +
                        "\t\"password\": \"1234\",\n" +
                        "\t\"role\": \"USER\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Save user with same email")
    void saveUserWithSameEmail() throws Exception {
        NewUserDTO newUser = NewUserDTO.builder()
                .firstName("John")
                .lastName("Smith")
                .email("test@email.com")
                .password("1234")
                .role("USER")
                .build();

        Mockito.when(_userService.create(newUser))
                .thenThrow(new DataIntegrityViolationException("User id already in use"));

        _mockMvc.perform(post("/api/v1/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"firstName\": \"John\",\n" +
                                "\t\"lastName\": \"Smith\",\n" +
                                "\t\"email\": \"test@email.com\",\n" +
                                "\t\"password\": \"1234\",\n" +
                                "\t\"role\": \"USER\"\n" +
                                "}"))
                .andExpect(status().isUnprocessableEntity());
    }

    private UUID _MOCK_UUID = UUID.fromString("3ce7d9ea-6978-4a8e-9b0a-77b998a1fda");

    private User _user;

    @MockBean
    private UserService _userService;

    @Autowired
    private MockMvc _mockMvc;
}