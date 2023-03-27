package kgkars.spring.modulith.playground.user.internal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User ID Format is not of type UUID")
public class InvalidUserIdFormat extends Exception {

    public InvalidUserIdFormat(String message) {
        super(message);
    }

    public InvalidUserIdFormat(String message, Throwable throwable) {
        super(message, throwable);
    }

}
