package ru.academy.Calendarapp.exception;

public class NoSuchEventException extends RuntimeException{

    public NoSuchEventException(String message) {
        super(message);
    }
}
