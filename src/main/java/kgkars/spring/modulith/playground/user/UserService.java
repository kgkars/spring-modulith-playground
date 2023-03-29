package kgkars.spring.modulith.playground.user;

import kgkars.spring.modulith.playground.common.dto.UserRegistrationRequest;
import kgkars.spring.modulith.playground.user.internal.exception.UserNotFoundException;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User create(UserRegistrationRequest request);

    Optional<User> findUserByEmail(String email) throws UserNotFoundException;

    User findUserById(UUID id) throws UserNotFoundException;
}
