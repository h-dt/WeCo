package com.dreamteam.hola.exception.file;

import com.amazonaws.services.s3.model.AmazonS3Exception;

public class NotExistException extends AmazonS3Exception {

    public NotExistException(String message) {
        super(message);
    }
}
