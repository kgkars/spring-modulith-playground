package kgkars.spring.modulith.playground.user.internal.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(value = InvalidUserIdFormat.class)
    public ResponseEntity<Object> handleInvalidUserIdFormatException(InvalidUserIdFormat exception) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationExceptions(DataIntegrityViolationException exception) {

        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ExceptionResponse response = new ExceptionResponse(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException exception) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Map<String, String> validationErrorMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrorMap.put(error.getField(), error.getDefaultMessage());
        });

        DTOValidationExceptionResponse response = new DTOValidationExceptionResponse(
                validationErrorMap,
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(response, httpStatus);
    }
}
