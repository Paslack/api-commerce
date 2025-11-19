package com.phc.dscommerce.repositories;

import com.phc.dscommerce.dto.ProductDTO;
import com.phc.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
