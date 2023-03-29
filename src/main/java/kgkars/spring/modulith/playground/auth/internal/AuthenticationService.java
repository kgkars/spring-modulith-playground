package kgkars.spring.modulith.playground.auth.internal;

import kgkars.spring.modulith.playground.common.dto.AuthenticationRequest;
import kgkars.spring.modulith.playground.common.dto.AuthenticationResponse;
import kgkars.spring.modulith.playground.common.service.JwtService;
import kgkars.spring.modulith.playground.user.User;
import kgkars.spring.modulith.playground.user.UserService;
import kgkars.spring.modulith.playground.user.internal.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException {
        _authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = _userService.findUserByEmail(request.getEmail())
                .orElseThrow();

        String jwtToken = _jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private final AuthenticationManager _authenticationManager;

    private final JwtService _jwtService;

    private final UserService _userService;
}
