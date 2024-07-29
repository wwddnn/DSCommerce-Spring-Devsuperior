package com.devsuperior.dscommerce.repositories;
import com.devsuperior.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//the name of interface is the entity name and suffix repository
//this component product repository is responsible operations on database of product
public interface ProductRepository extends JpaRepository <Product, Long>{

}
