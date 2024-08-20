package com.abreu.tests.exceptions.handler;

import com.abreu.tests.exceptions.NameAlreadyExistsException;
import com.abreu.tests.exceptions.ErrorMessage;
import com.abreu.tests.exceptions.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_PREFIX = "API Error - ";

    @ExceptionHandler({ ProductNotFoundException.class })
    public final ResponseEntity<ErrorMessage> handleEntityNotFoundException(
            RuntimeException ex, HttpServletRequest request
    ) {

        if (request == null) {
            log.error(ERROR_PREFIX, ex);
            return ResponseEntity.status(NOT_FOUND).contentType(APPLICATION_JSON).body(new ErrorMessage(NOT_FOUND, ex.getMessage()));
        }

        log.error(ERROR_PREFIX, ex);
        return ResponseEntity
                .status(NOT_FOUND)
                .contentType(APPLICATION_JSON)
                .body(new ErrorMessage(request, NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler({ NameAlreadyExistsException.class })
    public final ResponseEntity<ErrorMessage> handleConflict(
            RuntimeException ex, HttpServletRequest request
    ) {

        if (request == null) {
            log.error(ERROR_PREFIX, ex);
            return ResponseEntity.status(CONFLICT).contentType(APPLICATION_JSON).body(new ErrorMessage(CONFLICT, ex.getMessage()));
        }

        log.error(ERROR_PREFIX, ex);
        return ResponseEntity
                .status(CONFLICT)
                .contentType(APPLICATION_JSON)
                .body(new ErrorMessage(request, CONFLICT, ex.getMessage()));
    }

}
