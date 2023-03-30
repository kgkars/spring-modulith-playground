package kgkars.spring.modulith.playground.common;

import kgkars.spring.modulith.playground.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (user != null) {
                return user.getUserId().toString().describeConstable();
            }
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }
}

