package io.github.jinyoungyou.todo.exception.handler;

import io.github.jinyoungyou.todo.exception.*;
import io.github.jinyoungyou.todo.exception.dto.ErrorResponse;
import io.github.jinyoungyou.todo.exception.vo.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.RESOURCE_NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.EMAIL_ALREADY_EXISTS), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidJwtTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAccessTokenException(InvalidJwtTokenException e) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.INVALID_JWT_TOKEN), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleExpiredAuthenticationException(ExpiredAuthenticationException e) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.EXPIRED_AUTHENTICATION), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @ExceptionHandler(AuthenticationFailException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationFailException(AuthenticationFailException e) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.AUTHENTICATION_FAIL), HttpStatus.FORBIDDEN);
    }
}
