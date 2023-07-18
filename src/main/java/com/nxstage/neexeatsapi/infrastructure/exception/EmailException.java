package com.nxstage.neexeatsapi.infrastructure.exception;

public class EmailException extends  RuntimeException{
    static final long serialVersionUID = 1L;

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
