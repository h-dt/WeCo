package com.dreamteam.hola.exception.file;

public class NotAllowExtensionException extends IllegalArgumentException{

    public NotAllowExtensionException() {
    }

    public NotAllowExtensionException(String s) {
        super(s);
    }
}
