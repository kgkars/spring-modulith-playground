package kgkars.spring.modulith.playground.user.internal;

import kgkars.spring.modulith.playground.user.NewUserDTO;
import kgkars.spring.modulith.playground.user.Role;
import kgkars.spring.modulith.playground.user.User;
import kgkars.spring.modulith.playground.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public User create(NewUserDTO newUser) {
        User user = new User();

        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(_passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.valueOf(newUser.getRole()));

        _userRepository.save(user);

        return user;
    }

    private final PasswordEncoder _passwordEncoder;

    private final UserRepository _userRepository;
}
