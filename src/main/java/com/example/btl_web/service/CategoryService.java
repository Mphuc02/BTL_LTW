package com.example.btl_web.service;

import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.paging.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);
    CategoryDto findOneById(Long id);
    long countAllCategory();
    boolean save(CategoryDto categoryDto);
    boolean update(CategoryDto categoryDto);
    boolean delete(Long categoryId);
    boolean hidden(Long categoryId, Integer status);
}
