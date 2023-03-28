package ru.academy.Calendarapp.exception;

public class NoEventUserException extends RuntimeException{

    public NoEventUserException(String message) {
        super(message);
    }
}
