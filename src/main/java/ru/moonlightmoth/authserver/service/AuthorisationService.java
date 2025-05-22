package ru.moonlightmoth.authserver.service;

import ru.moonlightmoth.authserver.model.request.AuthorisationRequest;
import ru.moonlightmoth.authserver.model.response.AuthorisationResponse;

public interface AuthorisationService {

    AuthorisationResponse processAuthorisationRequest(AuthorisationRequest authorisationRequest);
}
