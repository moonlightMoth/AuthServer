package ru.moonlightmoth.authserver.service;

import ru.moonlightmoth.authserver.model.JwtToken;
import ru.moonlightmoth.authserver.model.entity.Role;
import ru.moonlightmoth.authserver.model.entity.UserDetails;
import ru.moonlightmoth.authserver.model.request.AuthorisationRequest;
import ru.moonlightmoth.authserver.model.request.LogInRequest;
import ru.moonlightmoth.authserver.model.response.AuthorisationResponse;
import ru.moonlightmoth.authserver.model.response.LogInResponse;

public interface JwtService {

    JwtToken generateToken(UserDetails userDetails);

    boolean isTokenValid(JwtToken jwtToken);

    Role extractRole(JwtToken jwtToken);

    String extractLogin(JwtToken jwtToken);
}
