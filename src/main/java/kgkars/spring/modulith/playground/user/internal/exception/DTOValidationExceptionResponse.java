package kgkars.spring.modulith.playground.user.internal.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class DTOValidationExceptionResponse {

    private final Map<String, String> errors;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
