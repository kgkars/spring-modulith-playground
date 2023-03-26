package kgkars.spring.modulith.playground.user;

import org.springframework.http.ProblemDetail;

public interface UserService {
    User create(NewUserDTO newUser);
}
