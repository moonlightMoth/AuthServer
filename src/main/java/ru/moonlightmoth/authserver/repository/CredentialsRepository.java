package ru.moonlightmoth.authserver.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.moonlightmoth.authserver.model.entity.UserDetails;

import java.util.Optional;

@Repository
@Primary
public interface CredentialsRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByLogin(String login);

}
