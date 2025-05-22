package ru.moonlightmoth.authserver.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.moonlightmoth.authserver.model.JwtToken;
import ru.moonlightmoth.authserver.model.entity.UserDetails;
import ru.moonlightmoth.authserver.model.request.AuthorisationRequest;
import ru.moonlightmoth.authserver.model.request.LogInRequest;
import ru.moonlightmoth.authserver.model.response.AuthorisationResponse;
import ru.moonlightmoth.authserver.repository.StubCredentialsRepository;
import ru.moonlightmoth.authserver.service.AuthorisationService;
import ru.moonlightmoth.authserver.service.JwtService;
import ru.moonlightmoth.authserver.service.LogInService;

@Validated
@RestController
public class AuthController {

    @Autowired
    private StubCredentialsRepository credentialsRepository;

    @Autowired
    private AuthorisationService authorisationService;

    @Autowired
    private LogInService logInService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LogInRequest logInRequest)
    {
        return ResponseEntity.ok(logInService.processLogInRequest(logInRequest));
    }

    @GetMapping("/authorise")
    public ResponseEntity<AuthorisationResponse> authorise(@Valid @RequestBody AuthorisationRequest authorisationRequest)
    {
        return ResponseEntity.ok(authorisationService.processAuthorisationRequest(authorisationRequest));
    }

}
