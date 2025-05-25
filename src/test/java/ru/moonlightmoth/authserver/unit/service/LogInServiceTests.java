package ru.moonlightmoth.authserver.unit.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.moonlightmoth.authserver.exception.InvalidPasswordException;
import ru.moonlightmoth.authserver.exception.NoSuchUserException;
import ru.moonlightmoth.authserver.model.JwtToken;
import ru.moonlightmoth.authserver.model.entity.Role;
import ru.moonlightmoth.authserver.model.entity.UserDetails;
import ru.moonlightmoth.authserver.model.request.LogInRequest;
import ru.moonlightmoth.authserver.model.response.Status;
import ru.moonlightmoth.authserver.repository.CredentialsRepository;
import ru.moonlightmoth.authserver.service.JwtService;
import ru.moonlightmoth.authserver.service.LogInService;
import ru.moonlightmoth.authserver.service.LogInServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LogInServiceTests {

    @Mock
    private CredentialsRepository credentialsRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private LogInService logInService = new LogInServiceImpl();

    @Test
    public void whenLogInNoUser_thenThrowException()
    {
        UserDetails userDetails = UserDetails.builder()
                .login("invalidLogin")
                .passwordHash("ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb") //password: "a"
                .id(1L)
                .name("name")
                .surname("surname")
                .role(Role.USER)
                .build();

        LogInRequest request = LogInRequest.builder()
                .login("invalidLogin")
                .password("a")
                .build();

        when(credentialsRepository.findByLogin("invalidLogin")).thenReturn(Optional.empty());

        assertThrows(NoSuchUserException.class, () -> logInService.processLogInRequest(request));

    }

    @Test
    public void whenLogInBadPassword_thenThrowException()
    {
        UserDetails userDetails = UserDetails.builder()
                .login("login")
                .passwordHash("ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb") //password: "a"
                .id(1L)
                .name("name")
                .surname("surname")
                .role(Role.USER)
                .build();

        LogInRequest request = LogInRequest.builder()
                .login("login")
                .password("b") //bad password
                .build();

        when(credentialsRepository.findByLogin("login")).thenReturn(Optional.ofNullable(userDetails));

        assertThrows(InvalidPasswordException.class, () -> logInService.processLogInRequest(request));
    }

    @Test
    public void whenLogInValidUser_thenSendOk()
    {
        UserDetails userDetails = UserDetails.builder()
                .login("login")
                .passwordHash("ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb") //password: "a"
                .id(1L)
                .name("name")
                .surname("surname")
                .role(Role.USER)
                .build();

        LogInRequest request = LogInRequest.builder()
                .login("login")
                .password("a") //valid password
                .build();

        when(jwtService.generateToken(Mockito.any())).thenReturn(new JwtToken("asd"));
        when(credentialsRepository.findByLogin("login")).thenReturn(Optional.ofNullable(userDetails));

        assertEquals(Status.OK, logInService.processLogInRequest(request).getStatus());
    }

}
