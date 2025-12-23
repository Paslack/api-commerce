package com.phc.dscommerce.services;

import com.phc.dscommerce.dto.CategoryDTO;
import com.phc.dscommerce.entities.Category;
import com.phc.dscommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> result = categoryRepository.findAll();
        return result.stream().map(x ->
                new CategoryDTO(x)).toList();
    }
}