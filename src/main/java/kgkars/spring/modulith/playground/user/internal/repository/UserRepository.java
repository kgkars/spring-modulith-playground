package kgkars.spring.modulith.playground.user.internal.repository;

import kgkars.spring.modulith.playground.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    Optional<User> findUserByFirstNameAndLastName(String firstName, String lastName);
}
