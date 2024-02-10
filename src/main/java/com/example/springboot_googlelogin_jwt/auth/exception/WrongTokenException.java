package com.example.springboot_googlelogin_jwt.auth.exception;

public class WrongTokenException extends RuntimeException{
    public WrongTokenException(String message) {
        super(message);
    }
}
