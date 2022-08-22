package com.dreamteam.hola.exception.file;

public class FileNameNullException extends NullPointerException{
    private static final long serialVersionUID = 1L;

    public FileNameNullException() {

    }

    public FileNameNullException(String message){
        super(message);
    }
}
