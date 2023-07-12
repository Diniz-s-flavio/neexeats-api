package com.nxstage.neexeatsapi.infrastructure.exception;

public class StorageException extends  RuntimeException{
    static final long serialVersionUID = 1L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
