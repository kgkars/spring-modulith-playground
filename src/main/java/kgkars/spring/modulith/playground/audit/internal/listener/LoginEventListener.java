package kgkars.spring.modulith.playground.audit.internal.listener;

import kgkars.spring.modulith.playground.audit.internal.entity.LoginAudit;
import kgkars.spring.modulith.playground.audit.internal.service.LoginAuditService;
import kgkars.spring.modulith.playground.auth.LoginEvent;
import kgkars.spring.modulith.playground.user.User;
import kgkars.spring.modulith.playground.user.UserService;
import kgkars.spring.modulith.playground.user.internal.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@Async
public class LoginEventListener implements ApplicationListener<LoginEvent> {


    @Override
    public void onApplicationEvent(LoginEvent event) {
        LoginAudit loginAuditRecord = new LoginAudit();

        try {
            Optional<User> user = _userService.findUserByEmail(event.getRequest().getEmail());

            log.info(event.getRequest().toString());

            log.info(user.get().getUsername());


            _loginAuditService.save(
                    loginAuditRecord.builder()
                            .username(user.get().getUsername())
                            .user(user.get())
                            .role(user.get().getRole().toString())
                            .requestUserAgent(event.getHttpServletRequest().getHeader("User-Agent"))
                            .build()
            );

        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private final LoginAuditService _loginAuditService;

    private final UserService _userService;
}
