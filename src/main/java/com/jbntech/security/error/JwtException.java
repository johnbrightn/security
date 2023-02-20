package com.jbntech.security.error;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class JwtException extends io.jsonwebtoken.JwtException {

    public JwtException(String message){
        super(message);
    }

    public JwtException(String message, Throwable cause){
        super(message, cause);
    }

}
