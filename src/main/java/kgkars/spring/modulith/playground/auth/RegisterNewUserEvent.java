package kgkars.spring.modulith.playground.auth;

import kgkars.spring.modulith.playground.common.dto.UserRegistrationRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@Slf4j
public class RegisterNewUserEvent extends ApplicationEvent {

    public RegisterNewUserEvent(UserRegistrationRequest request) {
        super(request);

        this.request = request;
    }

    private UserRegistrationRequest request;

}
