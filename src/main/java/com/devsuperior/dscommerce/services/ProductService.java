package com.devsuperior.dscommerce.services;
import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//implement the search on database, with dependency ProductRepository
@Service
public class ProductService {

    //dependency
    @Autowired
    private ProductRepository repository;

    //findById method to search products on database for id
    //only ready operation in database(don't lock in database)
    //first search product through id in database, after handle this product and save one variable, after convert this product in one product dto.
    //findById have method of optional, that throw exception
    //orElseThrow() throw exception case don't find object
    //this Service layer is responsible that throw the own exceptions.
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
            Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
            ProductDTO dto = new ProductDTO(product);
            return dto;
    }

    //findAll method to search all products on database
    //first search all products, after put them pageable and save on Page list, and after transform this list in one list ProductDTO.
    //obs. page is a stream. only user map() in this case, don't need use stream() here.
    //Pageable have import springframework data domain
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
            Page<Product> result = repository.findAll(pageable);
            return result.map(x -> new ProductDTO(x));
    }

    //this method will be saved the new product on database. 'read only' don't have here more.
    //copyDtoToEntity is method to copy dto for entity
    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    //getReferencebyId() this operation don't go on database
    //copyDtoToEntity is method to copy dto for entity
    @Transactional
    public ProductDTO update(Long id,  ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {
             throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    //method delete
    // is void, don't have return. delete code 204 no content
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    //create method for copy dto to entity
    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
    }
}



