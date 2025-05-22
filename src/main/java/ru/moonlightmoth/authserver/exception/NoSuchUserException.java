package ru.moonlightmoth.authserver.exception;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException(String s)
    {
        super(s);
    }
}
