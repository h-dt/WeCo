package com.dreamteam.hola.exception.comment;

public class CommentNotMatchException extends IllegalArgumentException{
    private static final long serialVersionUID = 1L;

    public CommentNotMatchException() {

    }

    public CommentNotMatchException (String message){
        super(message);
    }
}
