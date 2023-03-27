package kgkars.spring.modulith.playground.user.internal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User ID Format is not of type UUID")
public class InvalidUserIdFormatException extends Exception {

    public InvalidUserIdFormatException(String message) {
        super(message);
    }

    public InvalidUserIdFormatException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
