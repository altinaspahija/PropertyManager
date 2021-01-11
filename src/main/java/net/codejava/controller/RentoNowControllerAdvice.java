package net.codejava.controller;

import lombok.extern.slf4j.Slf4j;
import net.codejava.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class RentoNowControllerAdvice extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(UserNotFoundException ex){
        log.error("Requested User not found");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "User not found.");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PropertyNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(PropertyNotFoundException ex){
        log.error("Requested Property not found");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message","Property not found.");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(ReservationNotFoundException ex){
        log.error("Requested Reservation not found");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message","Reservation not found.");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PaymentDetailsNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(PaymentDetailsNotFoundException ex){
        log.error("Requested Reservation not found");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message","Payment Details not found.");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Object> handleBad(InvalidParameterException ex){
        log.error("InvalidParameterException");

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp",LocalDateTime.now());
        body.put("message","You entered an invalid combination of parameters.");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
