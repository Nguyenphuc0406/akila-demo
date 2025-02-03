package com.akila.demo.controller;

import com.akila.demo.dto.BaseErrorDto;
import java.util.Date;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Phuc Nguyen
 */
@RestControllerAdvice
@Log4j2
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException e)
    {
        BaseErrorDto errorDto = BaseErrorDto.builder()
                .timestamp(new Date().getTime())
                .errorMessage(e.getReason())
                .build();

        return ResponseEntity.status(e.getStatusCode()).body(errorDto);
    }
}
