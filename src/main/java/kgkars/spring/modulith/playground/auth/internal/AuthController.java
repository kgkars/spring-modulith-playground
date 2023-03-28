package kgkars.spring.modulith.playground.auth.internal;

import jakarta.validation.Valid;
import kgkars.spring.modulith.playground.auth.RegisterNewUserEvent;
import kgkars.spring.modulith.playground.common.dto.UserRegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRegistrationRequest request) {

        _eventPublisher.publishEvent(new RegisterNewUserEvent(request));

        return ResponseEntity.ok("Successfully registered");
    }

    private final ApplicationEventPublisher _eventPublisher;
}
