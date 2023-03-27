package kgkars.spring.modulith.playground.user;

import kgkars.spring.modulith.playground.common.dto.NewUserDTO;
import kgkars.spring.modulith.playground.user.internal.entity.User;

import java.util.UUID;

public interface UserService {
    User create(NewUserDTO newUser);

    User findUserById(UUID id);
}
