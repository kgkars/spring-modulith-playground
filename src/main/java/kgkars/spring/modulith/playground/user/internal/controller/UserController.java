package kgkars.spring.modulith.playground.user.internal.controller;

import jakarta.validation.Valid;
import kgkars.spring.modulith.playground.common.dto.UserRegistrationRequest;
import kgkars.spring.modulith.playground.user.UserService;
import kgkars.spring.modulith.playground.user.User;
import kgkars.spring.modulith.playground.user.internal.exception.InvalidUserIdFormatException;
import kgkars.spring.modulith.playground.user.internal.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody @Valid UserRegistrationRequest request) {
        return ResponseEntity.ok(_userService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findByUserId(@PathVariable("id") String id) throws UserNotFoundException, InvalidUserIdFormatException {
        try {
            UUID userId = UUID.fromString(id);
        }
        catch (Exception exception) {
            throw new InvalidUserIdFormatException("User ID format is incorrect.");
        }
        return ResponseEntity.ok(_userService.findUserById(UUID.fromString(id)));
    }

    private final UserService _userService;
}
