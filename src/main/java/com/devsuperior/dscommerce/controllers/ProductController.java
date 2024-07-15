package com.devsuperior.dscommerce.controllers;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//controller implements the resources
//controller that respond on route '/products'
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    //this annotation @Autowired is for injection component
    //dependency
    @Autowired
    private ProductService service;

    //method for return product dto.
    //this '/{id}' is for received several parameter on the route
    //pathvariable received several id on the route
    //findById() is the same method in class ProductService
    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return dto;
    }

    //pageable to view products by pages
    //findAll() is the same method in class ProductService
    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }
}
