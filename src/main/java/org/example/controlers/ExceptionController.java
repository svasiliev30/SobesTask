package org.example.controlers;

import org.example.check.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e){
       Response response = new Response("entered incorrect data, enter a number from 1 to 100 - " +
               e.getMessage());
       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
