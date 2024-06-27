package com.example.blogapis.exception;

import com.example.blogapis.payloads.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>(new APIResponse(message, false, HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> notValidArgsPassed(MethodArgumentNotValidException ex) {

        Map<String, String> resp = new HashMap<>();

        //getting all the errors and looping over the array of objects
        ex.getBindingResult().getAllErrors().forEach((e)->{
            //get the fieldName
             String fieldName = ((FieldError) e).getField();
             //get the message
             String message = e.getDefaultMessage();
             //put in the hashmap to print
             resp.put(fieldName,message);
        });

        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);

    }



}
