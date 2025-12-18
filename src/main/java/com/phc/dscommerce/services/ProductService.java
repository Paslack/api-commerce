package com.phc.dscommerce.services;

import com.phc.dscommerce.dto.ProductDTO;
import com.phc.dscommerce.entities.Product;
import com.phc.dscommerce.exceptions.DatabaseException;
import com.phc.dscommerce.exceptions.ResourceNotFoundException;
import com.phc.dscommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable) {
        Page<Product> result = productRepository.searchByName(name, pageable);
        return result.map(x ->
                new ProductDTO(
                        x.getId(),
                        x.getName(),
                        x.getDescription(),
                        x.getPrice(),
                        x.getImgUrl()));
    }

    @Transactional
    public ProductDTO insertProduct(ProductDTO productDTO) {
        Product product = new Product();
        copyDtoToEntity(productDTO, product);
        productRepository.save(product);
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), productDTO.getImgUrl());
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        try {
            Product product = productRepository.getReferenceById(id);
            copyDtoToEntity(productDTO, product);
            productRepository.save(product);
            return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), productDTO.getImgUrl());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
    }
}