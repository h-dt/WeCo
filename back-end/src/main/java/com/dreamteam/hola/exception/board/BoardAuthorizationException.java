package com.dreamteam.hola.exception.board;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BoardAuthorizationException extends NullPointerException{


    public BoardAuthorizationException(String message) {
        super(message);
    }
}
