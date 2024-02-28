package com.br.joaoptgaino.jwtauthlearning.infra.exceptions;


import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String username;

    public UserNotFoundException(String username) {
        super(String.format("User not found %s", username));
        this.username = username;
    }
}
