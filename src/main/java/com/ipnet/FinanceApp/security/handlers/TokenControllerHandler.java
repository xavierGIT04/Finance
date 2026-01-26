package com.ipnet.FinanceApp.security.handlers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ipnet.FinanceApp.security.exception.ErrorResponse;
import com.ipnet.FinanceApp.security.exception.TokenException;

import java.time.Instant;

@RestControllerAdvice
public class TokenControllerHandler {

    @ExceptionHandler(value = TokenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleRefreshTokenException(TokenException ex, WebRequest request){
       final ErrorResponse errorResponse = new  ErrorResponse(
    		  HttpStatus.FORBIDDEN.value(),
    		  "Invalid Token",
              Instant.now(),
              ex.getMessage(),
              request.getDescription(false));
        return new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }
}