package ru.moonlightmoth.authserver.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.moonlightmoth.authserver.exception.InvalidPasswordException;
import ru.moonlightmoth.authserver.exception.NoSuchUserException;
import ru.moonlightmoth.authserver.model.JwtToken;
import ru.moonlightmoth.authserver.model.entity.UserDetails;
import ru.moonlightmoth.authserver.repository.CredentialsRepository;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret.sha256}")
    private String jwtSecret; //secret must be provided in environmental variables in production
    private SecretKey key;

    @Autowired
    private CredentialsRepository credentialsRepository;

    @PostConstruct
    private void init()
    {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public JwtToken generateToken(UserDetails userDetails)
    {
        Map<String, Object> claims = new HashMap<>();

        claims.put("name", userDetails.getName());
        claims.put("surname", userDetails.getSurname());
        claims.put("role", userDetails.getRole());
        claims.put("login", userDetails.getLogin());
        claims.put("iat", System.currentTimeMillis());

        return new JwtToken(Jwts.builder().claims(claims)
                .subject(userDetails.getName()).signWith(key).compact());
    }

    @Override
    public boolean isTokenValid(JwtToken jwtToken)
    {
        // Throws SignatureException if token invalid
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwtToken.getToken());

        Claims claims = claimsJws.getPayload();
        String login = (String) claims.get("login");

        if (!verifyClaimsByLogin(claims, login))
            throw new NoSuchUserException("No such user or user data invalid");

        return true;
    }

    @Override
    public Claims extractClaims(JwtToken jwtToken)
    {
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwtToken.getToken());

        Claims claims = claimsJws.getPayload();

        verifyClaims(claims);

        return claims;
    }

    private void verifyClaims(Claims claims)
    {
        String login = claims.get("login", String.class);
        String name = claims.get("login", String.class);
        String surname = claims.get("surname", String.class);

        if (login == null || login.isEmpty() || login.isBlank())
            throw new InvalidPasswordException("User has no login");

        if (name == null || name.isEmpty() || name.isBlank())
            throw new InvalidPasswordException("User has no name");

        if (surname == null || surname.isEmpty() || surname.isBlank())
            throw new InvalidPasswordException("User has no surname");

        String role = claims.get("role", String.class);

        if (!(role.equals("ADMIN") || role.equals("USER")))
            throw new InvalidPasswordException("User has no Role");
    }

    private boolean verifyClaimsByLogin(Claims claims, String login)
    {
        if (login == null || login.isBlank() || login.isEmpty())
            return false;

        Optional<UserDetails> userDetailsOptional = credentialsRepository.findByLogin(login);

        if (userDetailsOptional.isEmpty())
            throw new NoSuchUserException("User invalid");

        UserDetails userDetails = userDetailsOptional.get();

        String name = claims.get("name", String.class);
        String surname = claims.get("surname", String.class);
        String role = claims.get("role", String.class);

        return userDetails.getName().equals(name) &&
                userDetails.getSurname().equals(surname) &&
                userDetails.getRole().toString().equals(role);
    }
}
