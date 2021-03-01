package com.solactive.tickservice.exception;

import javax.ws.rs.core.Response.Status;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ApiException extends RuntimeException {

    private String message;
    private int statusCode = 500;

    public ApiException(String message) {
        this.message = message;
    }

    public ApiException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ApiException(String message, Status status) {
        this.message = message;
        this.statusCode = status.getStatusCode();
    }

}