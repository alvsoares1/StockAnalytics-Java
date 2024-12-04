package com.example.stockanalytics.exceptions;

public class FailedLoginException extends RuntimeException {
    public FailedLoginException() {super("Failed to login");}
    public FailedLoginException(String message) {super(message);}
}
