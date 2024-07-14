package com.devsuperior.dscommerce.services;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

//is here, go implement the search on database
@Service
public class ProductService {

    //dependency
    @Autowired
    private ProductRepository repository;

    //method
    @Transactional(readOnly = true) //only ready operation in database(dont lock in database)
    public ProductDTO findById(Long id) {
        Optional<Product> result = repository.findById(id); //search on database the product with method findById()
        Product product = result.get(); //get the product
        ProductDTO dto = new ProductDTO(product); //convert product for one product dto
        return dto;
    }

}
