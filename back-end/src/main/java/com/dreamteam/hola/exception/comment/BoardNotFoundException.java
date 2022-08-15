package com.dreamteam.hola.exception.comment;

public class BoardNotFoundException extends NullPointerException{
    private static final long serialVersionUID = 1L;

    public BoardNotFoundException() {

    }

    public BoardNotFoundException(String message){
        super(message);
    }
}
