package com.phc.dscommerce.services;

import com.phc.dscommerce.dto.ProductDTO;
import com.phc.dscommerce.entities.Product;
import com.phc.dscommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = result.get();
        ProductDTO dto = new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
        return dto;
    }
}
