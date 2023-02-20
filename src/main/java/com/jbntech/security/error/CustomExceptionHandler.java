package com.jbntech.security.error;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, WebRequest wr){

        String requestUri = ((ServletWebRequest)wr).getRequest().getRequestURI().toString();
        ExceptionMessage exceptionMessage = ExceptionMessage.builder().message(ex.getMessage()).path(requestUri).build();

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {ChangeSetPersister.NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(ChangeSetPersister.NotFoundException ex, WebRequest wr){
        String requestUri = ((ServletWebRequest)wr).getRequest().getRequestURI().toString();
        ExceptionMessage exceptionMessage = ExceptionMessage.builder().message("Token Not Found in header").path(requestUri).build();

        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
