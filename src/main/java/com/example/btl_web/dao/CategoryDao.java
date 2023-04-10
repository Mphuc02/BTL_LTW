package com.example.btl_web.dao;

import com.example.btl_web.dto.CategoryDto;

import java.util.List;

public interface CategoryDao{
    List<CategoryDto> findAll();
    CategoryDto findOne(Long id);
    List<CategoryDto> findAllByName(String key);
}
