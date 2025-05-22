package ru.moonlightmoth.authserver.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import ru.moonlightmoth.authserver.model.entity.Role;
import ru.moonlightmoth.authserver.model.entity.UserDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class StubCredentialsRepository implements CredentialsRepository {

    HashMap<String, UserDetails> userDetails = new HashMap<>();

    @PostConstruct
    private void init()
    {
        userDetails.put("login1",
                UserDetails.builder()
                        .login("login1")
                        .passwordHash("0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e")
                        .name("name1")
                        .surname("surname1")
                        .role(Role.USER)
                        .build());

        userDetails.put("login2",
                UserDetails.builder()
                        .login("login2")
                        .passwordHash("6cf615d5bcaac778352a8f1f3360d23f02f34ec182e259897fd6ce485d7870d4")
                        .name("name2")
                        .surname("surname2")
                        .role(Role.USER)
                        .build());

        userDetails.put("login3",
                UserDetails.builder()
                        .login("login3")
                        .passwordHash("5906ac361a137e2d286465cd6588ebb5ac3f5ae955001100bc41577c3d751764")
                        .name("name3")
                        .surname("surname3")
                        .role(Role.USER)
                        .build());
    }

    public Optional<UserDetails> findByLogin(String login)
    {
        if (!userDetails.containsKey(login))
            return Optional.empty();

        return Optional.of(userDetails.get(login));
    }

    @Override
    public void flush()
    {

    }

    @Override
    public <S extends UserDetails> S saveAndFlush(S entity)
    {
        return null;
    }

    @Override
    public <S extends UserDetails> List<S> saveAllAndFlush(Iterable<S> entities)
    {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<UserDetails> entities)
    {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs)
    {

    }

    @Override
    public void deleteAllInBatch()
    {

    }

    @Override
    public UserDetails getOne(Long aLong)
    {
        return null;
    }

    @Override
    public UserDetails getById(Long aLong)
    {
        return null;
    }

    @Override
    public UserDetails getReferenceById(Long aLong)
    {
        return null;
    }

    @Override
    public <S extends UserDetails> Optional<S> findOne(Example<S> example)
    {
        return Optional.empty();
    }

    @Override
    public <S extends UserDetails> List<S> findAll(Example<S> example)
    {
        return null;
    }

    @Override
    public <S extends UserDetails> List<S> findAll(Example<S> example, Sort sort)
    {
        return null;
    }

    @Override
    public <S extends UserDetails> Page<S> findAll(Example<S> example, Pageable pageable)
    {
        return null;
    }

    @Override
    public <S extends UserDetails> long count(Example<S> example)
    {
        return 0;
    }

    @Override
    public <S extends UserDetails> boolean exists(Example<S> example)
    {
        return false;
    }

    @Override
    public <S extends UserDetails, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction)
    {
        return null;
    }

    @Override
    public <S extends UserDetails> S save(S entity)
    {
        return null;
    }

    @Override
    public <S extends UserDetails> List<S> saveAll(Iterable<S> entities)
    {
        return null;
    }

    @Override
    public Optional<UserDetails> findById(Long aLong)
    {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong)
    {
        return false;
    }

    @Override
    public List<UserDetails> findAll()
    {
        return null;
    }

    @Override
    public List<UserDetails> findAllById(Iterable<Long> longs)
    {
        return null;
    }

    @Override
    public long count()
    {
        return 0;
    }

    @Override
    public void deleteById(Long aLong)
    {

    }

    @Override
    public void delete(UserDetails entity)
    {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs)
    {

    }

    @Override
    public void deleteAll(Iterable<? extends UserDetails> entities)
    {

    }

    @Override
    public void deleteAll()
    {

    }

    @Override
    public List<UserDetails> findAll(Sort sort)
    {
        return null;
    }

    @Override
    public Page<UserDetails> findAll(Pageable pageable)
    {
        return null;
    }
}
