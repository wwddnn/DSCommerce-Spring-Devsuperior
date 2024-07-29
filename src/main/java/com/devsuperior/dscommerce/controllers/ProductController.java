package com.devsuperior.dscommerce.controllers;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

//controller implements the resources
//controller that respond on route '/products'
@RestController
@RequestMapping(value = "/products")
public class ProductController {

    //this annotation @Autowired is for injection component
    //dependency
    @Autowired
    private ProductService service;

    //method findById
    //method for return product dto.
    //this '/{id}' is for received several parameter on the route
    //pathvariable received several id on the route
    //ResponseEntity customize the response 200
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    //method findAll
    //Pageable and Page, go to view products by pages
    //Pageable have import springframework data domain
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<ProductDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    //method insert
    //insert() method is a post method, and go save on database the body inserted on postman
    //@RequestBody is annotation to insert data on the body of request
    //instantiate object URI, is the good practice. the response come with code 201 and resource link
    //@Valid take the body validation that is a dto come json. this annotation come to Bean Validation in ProductDTO.
    @PostMapping
    public ResponseEntity<ProductDTO> insert( @Valid @RequestBody ProductDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    //method update
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    //method delete
    //delete code return ''204 no content'', because don't have body.
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
