package ru.moonlightmoth.authserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

        return AuthorisationResponse.builder()
                .role(jwtService.extractRole(authorisationRequest.getToken()))
                .status(Status.OK)
                .message("Access granted")
                .build();
    }
}
