package ru.moonlightmoth.authserver.service;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.moonlightmoth.authserver.model.entity.Role;
import ru.moonlightmoth.authserver.model.request.AuthorisationRequest;
import ru.moonlightmoth.authserver.model.response.AuthorisationResponse;
import ru.moonlightmoth.authserver.model.response.Status;

@Service
public class AuthorisationServiceImpl implements AuthorisationService{

    @Autowired
    JwtService jwtService;

    @Override
    public AuthorisationResponse processAuthorisationRequest(AuthorisationRequest authorisationRequest)
    {
        jwtService.isTokenValid(authorisationRequest.getToken());

        Claims claims = jwtService.extractClaims(authorisationRequest.getToken());

        return AuthorisationResponse.builder()
                .role(Role.fromString(claims.get("role", String.class)))
                .login(claims.get("login", String.class))
                .name(claims.get("name", String.class))
                .surname(claims.get("surname", String.class))
                .status(Status.OK)
                .message("Access granted")
                .build();
    }
}
