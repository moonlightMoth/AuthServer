package ru.moonlightmoth.authserver.controller;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.moonlightmoth.authserver.exception.InvalidUserException;
import ru.moonlightmoth.authserver.exception.NoSuchUserException;
import ru.moonlightmoth.authserver.model.response.AuthorisationResponse;
import ru.moonlightmoth.authserver.model.response.LogInResponse;
import ru.moonlightmoth.authserver.model.response.Status;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<AuthorisationResponse> handleSignatureException(SignatureException e)
    {
        return ResponseEntity.badRequest().body(AuthorisationResponse
                .builder()
                .status(Status.FAIL)
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<LogInResponse> handleNoSuchUserException(NoSuchUserException e)
    {
        return ResponseEntity.badRequest().body(LogInResponse.builder()
                .status(Status.FAIL)
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<LogInResponse> handleInvalidUserException(InvalidUserException e)
    {
        return ResponseEntity.badRequest().body(LogInResponse.builder()
                .status(Status.FAIL)
                .message(e.getMessage())
                .build());
    }
}
