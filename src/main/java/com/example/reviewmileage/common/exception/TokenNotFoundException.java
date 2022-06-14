package com.example.reviewmileage.common.exception;

public class TokenNotFoundException extends RuntimeException{
    private static final String MESSAGE = "토큰이 잘못됐습니다.";

    public TokenNotFoundException() {
        super(MESSAGE);
    }
}
