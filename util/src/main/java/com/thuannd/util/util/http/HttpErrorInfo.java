package com.thuannd.util.util.http;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class HttpErrorInfo {

    private ZonedDateTime timestamp;
    private String path;
    private HttpStatus httpStatus;
    private String message;

    public HttpErrorInfo(){
        timestamp = null;
        httpStatus = null;
        path = null;
        message = null;
    }

    public HttpErrorInfo(String path, HttpStatus httpStatus, String message){
        this.path = path;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpErrorInfo(ZonedDateTime timestamp, String path, HttpStatus httpStatus, String message) {
        this.timestamp = timestamp;
        this.path = path;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}