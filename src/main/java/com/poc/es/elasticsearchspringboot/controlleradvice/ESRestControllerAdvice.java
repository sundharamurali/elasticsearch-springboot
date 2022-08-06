package com.poc.es.elasticsearchspringboot.controlleradvice;

import com.poc.es.elasticsearchspringboot.exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class ESRestControllerAdvice {
    @Getter
    @Setter
    @AllArgsConstructor
    class Error{
        private String message;
        private int code;
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Error> handleRecordNotFoundException(RecordNotFoundException exception) {
        log.info(exception.getMessage());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Error> handleIOException(IOException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.internalServerError().body(new Error(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.internalServerError().body(new Error(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
