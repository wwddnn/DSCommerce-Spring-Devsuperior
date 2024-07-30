package com.devsuperior.dscommerce.dto;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

//this is subclass of CustomError
public class ValidationError extends CustomError {

    private List<FieldMessage> errors = new ArrayList<>();

    //constructor with arguments, and super
    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    //get method for the List
    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }

}
