package ru.moonlightmoth.authserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.moonlightmoth.authserver.model.JwtToken;
import ru.moonlightmoth.authserver.model.entity.UserDetails;
import ru.moonlightmoth.authserver.model.request.AuthorisationRequest;
import ru.moonlightmoth.authserver.model.request.LogInRequest;
import ru.moonlightmoth.authserver.model.response.AuthorisationResponse;
import ru.moonlightmoth.authserver.model.response.LogInResponse;
import ru.moonlightmoth.authserver.model.response.Status;
import ru.moonlightmoth.authserver.repository.StubCredentialsRepository;
import ru.moonlightmoth.authserver.service.AuthorisationService;
import ru.moonlightmoth.authserver.service.JwtService;
import ru.moonlightmoth.authserver.service.LogInService;

@Validated
@RestController
@RequestMapping("/")
@Tag(name = "Auth API", description = "Manages login and authorisation")
public class AuthController {
    @Autowired
    private AuthorisationService authorisationService;
    @Autowired
    private LogInService logInService;

    @PostMapping("/login")
    @Operation(summary = "User login endpoint")
    @ApiResponse(description = "Response status OK", responseCode = "200",
            content = @Content(schema = @Schema(implementation = LogInResponse.class)))
    @ApiResponse(description = "Response status FAIL", responseCode = "403",
            content = @Content(schema = @Schema(implementation = LogInResponse.class)))
    public ResponseEntity<LogInResponse> login(@Valid @RequestBody LogInRequest logInRequest)
    {
        LogInResponse response = logInService.processLogInRequest(logInRequest);

        if (response.getStatus() == Status.OK)
            return ResponseEntity.ok(response);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @PostMapping("/authorise")
    @Operation(summary = "User authorisation endpoint")
    @ApiResponse(description = "Response status OK", responseCode = "200",
            content = @Content(schema = @Schema(implementation = AuthorisationResponse.class)))
    @ApiResponse(description = "Response status FAIL", responseCode = "403",
            content = @Content(schema = @Schema(implementation = AuthorisationResponse.class)))
    public ResponseEntity<AuthorisationResponse> authorise(@Valid @RequestBody AuthorisationRequest authorisationRequest)
    {
        AuthorisationResponse response = authorisationService.processAuthorisationRequest(authorisationRequest);

        if (response.getStatus() == Status.OK)
            return ResponseEntity.ok(response);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

}
