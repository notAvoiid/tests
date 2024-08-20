package com.abreu.tests.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Date;

@ToString
@Getter
public class ErrorMessage {

    private String path;
    private String method;
    private final int status;
    private final String statusText;
    private final Date timestamp;
    private final String message;

    public ErrorMessage(HttpStatus status, String message) {
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.timestamp = new Date();
        this.message = message;
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.timestamp = new Date();
        this.message = message;
    }
}