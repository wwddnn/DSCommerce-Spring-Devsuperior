package com.devsuperior.dscommerce.services;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

//implement the search on database, with dependency ProductRepository
@Service
public class ProductService {

    //dependency
    @Autowired
    private ProductRepository repository;

    //findById method to search products on database for id
    //only ready operation in database(don't lock in database)
    //first search product through id, after handle this product and save one variable, after convert this product in one product dto.
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
            Optional<Product> result = repository.findById(id);
            Product product = result.get();
            ProductDTO dto = new ProductDTO(product);
            return dto;
    }

    //findAll method to search all products on database
    //first search all products, after put them pageable and save on Page list, and after transform this list in one list ProductDTO.
    //obs. page is a stream. only user map() in this case, don't need use stream() here.
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
            Page<Product> result = repository.findAll(pageable);
            return result.map(x -> new ProductDTO(x));

    }
}
