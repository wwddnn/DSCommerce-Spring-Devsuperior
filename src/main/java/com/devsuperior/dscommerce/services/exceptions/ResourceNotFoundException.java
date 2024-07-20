package com.devsuperior.dscommerce.services.exceptions;
//are exceptions on my service layer
//my class of exception customized when don't meet the resource
//have extends to exception on java(RuntimeException)
//RuntimeException don't require that put block try catch
public class ResourceNotFoundException extends RuntimeException {

    //constructor
    public ResourceNotFoundException(String msg) {
            super(msg);
    }
}
