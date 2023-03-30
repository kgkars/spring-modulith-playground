package kgkars.spring.modulith.playground.auth;

import jakarta.servlet.http.HttpServletRequest;
import kgkars.spring.modulith.playground.common.dto.AuthenticationRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@Slf4j
public class LoginEvent extends ApplicationEvent {

    public LoginEvent(AuthenticationRequest request, HttpServletRequest httpServletRequest) {
        super(request);

        this.request = request;
        this.httpServletRequest = httpServletRequest;
    }

    private AuthenticationRequest request;
    private HttpServletRequest httpServletRequest;
}
