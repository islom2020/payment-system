package com.example.paymentsysteminjava.exception;

public class JwtExpiredTokenException extends RuntimeException {
    public JwtExpiredTokenException(String message) {
        super(message);
    }
}
