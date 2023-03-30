package kgkars.spring.modulith.playground.auth.internal;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kgkars.spring.modulith.playground.auth.LoginEvent;
import kgkars.spring.modulith.playground.auth.RegisterNewUserEvent;
import kgkars.spring.modulith.playground.common.dto.AuthenticationRequest;
import kgkars.spring.modulith.playground.common.dto.UserRegistrationRequest;
import kgkars.spring.modulith.playground.user.internal.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserRegistrationRequest request) {

        _eventPublisher.publishEvent(new RegisterNewUserEvent(request));

        return ResponseEntity.ok("Registration request received.");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody AuthenticationRequest request,
            HttpServletRequest httpServletRequest
    ) throws UserNotFoundException {

        _eventPublisher.publishEvent(new LoginEvent(request, httpServletRequest));

        return ResponseEntity.ok(_authenticationService.authenticate(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws UserNotFoundException {
        return ResponseEntity.ok(_authenticationService.authenticate(request));
    }

    private final ApplicationEventPublisher _eventPublisher;

    private final AuthenticationService _authenticationService;
}
