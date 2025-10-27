package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
   private LocalDateTime timestamp;
    private int status;
    private String error;
    private  String message;
    private  List<String> errors;


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(int status, String error, String message, List<String> errors) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }


    public static ErrorResponse badRequest(String message) {
        return new ErrorResponse(400,"Bad Request",message);
    }

    public static ErrorResponse badRequest(String message, List<String> errors) {
        return new ErrorResponse(400,"Bad Request",message,errors);
    }
}
