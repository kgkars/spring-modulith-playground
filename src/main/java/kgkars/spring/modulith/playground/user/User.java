package kgkars.spring.modulith.playground.user;

import jakarta.persistence.*;
import kgkars.spring.modulith.playground.common.Role;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
@Table(name = "tbl_user", uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email"))
@Slf4j
public class User extends AbstractAggregateRoot<User> implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(length = 60)
    private String password;

    private boolean enabled;
    private boolean locked;
    private boolean expired;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
