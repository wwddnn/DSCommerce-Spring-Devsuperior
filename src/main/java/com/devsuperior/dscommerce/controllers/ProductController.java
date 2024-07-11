package com.devsuperior.dscommerce.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//controller implements the resources
@RestController //controller that respond on route '/products'
@RequestMapping(value = "/products") //endpoint to respond on route '/products'
public class ProductController {

    @GetMapping //get method
    public String teste() {
        return "Ola mundo!";
    }

}
