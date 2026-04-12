package com.example.springboot_4_initial.exceptions.auth;

public class TokenJwtInvalid extends RuntimeException {
    public TokenJwtInvalid(String message) {
        super(message);
    }
}
