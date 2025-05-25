package ru.moonlightmoth.authserver.unit.service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.moonlightmoth.authserver.model.JwtToken;
import ru.moonlightmoth.authserver.model.entity.Role;
import ru.moonlightmoth.authserver.model.entity.UserDetails;
import ru.moonlightmoth.authserver.repository.CredentialsRepository;
import ru.moonlightmoth.authserver.service.JwtService;
import ru.moonlightmoth.authserver.service.JwtServiceImpl;

import javax.crypto.SecretKey;
import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTests {
    byte[] keyBytes = Decoders.BASE64.decode("SECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRETSECRET");
    SecretKey key = Keys.hmacShaKeyFor(keyBytes);
    @Mock
    private CredentialsRepository credentialsRepository;

    @InjectMocks
    private JwtService jwtService = new JwtServiceImpl();

    @Test
    public void whenGenerateTokenThenIsTokenValid_thenOk() throws NoSuchFieldException, IllegalAccessException
    {
        UserDetails userDetails = UserDetails.builder()
                .login("a")
                .id(1L)
                .role(Role.USER)
                .name("b")
                .surname("c")
                .passwordHash("asd")
                .build();

        when(credentialsRepository.findByLogin(Mockito.anyString())).thenReturn(Optional.ofNullable(userDetails));

        Field field = JwtServiceImpl.class.getDeclaredField("key");
        field.setAccessible(true);
        field.set(jwtService, key);

        JwtToken jwtToken = jwtService.generateToken(userDetails);

        assertTrue(jwtService.isTokenValid(jwtToken));

    }

    @Test
    public void whenInvalidTokenIsTokenValid_thenThrowException() throws NoSuchFieldException, IllegalAccessException
    {

        Field field = JwtServiceImpl.class.getDeclaredField("key");
        field.setAccessible(true);
        field.set(jwtService, key);

        JwtToken jwtToken = JwtToken.builder().token("eyJhbGciOiJIUzM4NCJ9" +
                ".eyJyb2xlIjoiVVNFUiIsInN1cm5hbWUiOiLQodC10LnRhNGD0LvQu9C40L0iLCJuYW1lIjoi0JbQsNC90LDRgiIsImxvZ2luIjoiaXZhbiIsImlhdCI6MTc0ODE4ODE1NTA3OCwic3ViIjoi0JbQsNC90LDRgiJ9" +
                ".OWLnXe234235Kes9UAK-oY4124jCXcFfeXJG6_JzrrzlX47C9Nt").build();

        assertThrows(SignatureException.class, ()->jwtService.isTokenValid(jwtToken));

    }

}
