package com.devsuperior.dscommerce.controllers;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//controller implements the resources
//controller that respond on route '/products'
@RestController
//endpoint to respond on route '/products'
@RequestMapping(value = "/products")
public class ProductController {

    //this annotation @Autowired is for injection component
    //dependency
    @Autowired
    private ProductService service;

    //method for return product dto
    @GetMapping(value = "/{id}") //this '/{id}' is for received several parameter on the route
    public ProductDTO findById(@PathVariable Long id) { //pathvariable received several id on the route //same method in class ProductService
         ProductDTO dto = service.findById(id);
        return dto;
    }
}
