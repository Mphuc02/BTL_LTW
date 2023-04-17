package com.example.btl_web.service;

import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.paging.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable, CategoryDto dto);
    CategoryDto findOneBy(CategoryDto dto);
    long countCategories();
    Long save(CategoryDto categoryDto);
    Long update(CategoryDto categoryDto);
    Long delete(Long categoryId);
}
