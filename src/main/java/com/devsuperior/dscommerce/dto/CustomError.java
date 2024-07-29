package com.devsuperior.dscommerce.dto;
import java.time.Instant;

//this object goes correspond the error in format json (like postman with attributes: timestamp, status, error, path)
public class CustomError {

    //attributes
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

    //constructor. obs only constructor full, don't have constructor empty
    public CustomError(Instant timestamp, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    //only get method (set don't)
    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

}
