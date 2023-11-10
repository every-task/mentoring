package com.playdata.exception;

public class StatusNotAcceptedException extends RuntimeException{
    public StatusNotAcceptedException(String message) {
        super(message);
    }
}
