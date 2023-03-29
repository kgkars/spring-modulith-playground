package kgkars.spring.modulith.playground.config;

import kgkars.spring.modulith.playground.user.UserService;
import kgkars.spring.modulith.playground.user.internal.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(_passwordEncoder);

        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            try {
                return _userService.findUserByEmail(username)
                        .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private final PasswordEncoder _passwordEncoder;

    private final UserService _userService;
}
