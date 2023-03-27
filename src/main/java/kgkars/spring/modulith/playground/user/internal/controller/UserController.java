package kgkars.spring.modulith.playground.user.internal.controller;

import jakarta.validation.Valid;
import kgkars.spring.modulith.playground.common.dto.NewUserDTO;
import kgkars.spring.modulith.playground.user.UserService;
import kgkars.spring.modulith.playground.user.internal.entity.User;
import kgkars.spring.modulith.playground.user.internal.exception.InvalidUserIdFormat;
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
    public ResponseEntity<Object> create(@RequestBody @Valid NewUserDTO newUser) {
        return ResponseEntity.ok(_userService.create(newUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findByUserId(@PathVariable("id") String id) throws UserNotFoundException, InvalidUserIdFormat {
        try {
            UUID userId = UUID.fromString(id);
        }
        catch (Exception exception) {
            throw new InvalidUserIdFormat("User ID format is incorrect.");
        }
        return ResponseEntity.ok(_userService.findUserById(UUID.fromString(id)));
    }

    private final UserService _userService;
}
