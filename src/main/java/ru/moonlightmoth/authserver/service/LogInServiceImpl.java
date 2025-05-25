package ru.moonlightmoth.authserver.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.moonlightmoth.authserver.exception.InvalidPasswordException;
import ru.moonlightmoth.authserver.exception.NoSuchUserException;
import ru.moonlightmoth.authserver.model.entity.UserDetails;
import ru.moonlightmoth.authserver.model.request.LogInRequest;
import ru.moonlightmoth.authserver.model.response.LogInResponse;
import ru.moonlightmoth.authserver.model.response.Status;
import ru.moonlightmoth.authserver.repository.CredentialsRepository;

import java.util.Optional;

@Service
public class LogInServiceImpl implements LogInService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public LogInResponse processLogInRequest(LogInRequest logInRequest)
    {
        Optional<UserDetails> userDetailsOptional = credentialsRepository.findByLogin(logInRequest.getLogin());
        if (userDetailsOptional.isEmpty())
            throw new NoSuchUserException("No such user or password is incorrect");

        UserDetails userDetails = userDetailsOptional.get();

        String passwordHash = DigestUtils.sha256Hex(logInRequest.getPassword());
        if (!passwordHash.equals(userDetails.getPasswordHash()))
            throw new InvalidPasswordException("No such user or password is incorrect");

        return LogInResponse.builder()
                .token(jwtService.generateToken(userDetails))
                .name(userDetails.getName())
                .surname(userDetails.getSurname())
                .status(Status.OK)
                .role(userDetails.getRole())
                .build();
    }
}
