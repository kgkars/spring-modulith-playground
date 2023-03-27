package kgkars.spring.modulith.playground.user.internal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

}
