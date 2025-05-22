package ru.moonlightmoth.authserver.service;

import ru.moonlightmoth.authserver.model.request.LogInRequest;
import ru.moonlightmoth.authserver.model.response.LogInResponse;

public interface LogInService {

    LogInResponse processLogInRequest(LogInRequest logInRequest);
}
