package kgkars.spring.modulith.playground.user.internal.controller;

import kgkars.spring.modulith.playground.common.dto.NewUserDTO;
import kgkars.spring.modulith.playground.user.internal.entity.User;
import kgkars.spring.modulith.playground.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody NewUserDTO newUser) {
        return ResponseEntity.ok(_userService.create(newUser));
    }

    private final UserService _userService;
}