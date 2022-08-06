package com.poc.es.elasticsearchspringboot.exception;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message) {
        super(message);
    }
}
