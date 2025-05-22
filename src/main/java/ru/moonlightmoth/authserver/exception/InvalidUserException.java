package ru.moonlightmoth.authserver.exception;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String message)
    {
        super(message);
    }
}
