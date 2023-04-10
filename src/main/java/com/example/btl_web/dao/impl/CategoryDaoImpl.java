package com.example.btl_web.dao.impl;

import com.example.btl_web.dao.CategoryDao;
import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.model.Category;

import java.util.List;

public class CategoryDaoImpl extends GeneralDaoImpl<Category> implements CategoryDao {

    @Override
    public List<CategoryDto> findAll() {
        return null;
    }

    @Override
    public CategoryDto findOne(Long id) {
        return null;
    }

    @Override
    public List<CategoryDto> findAllByName(String key) {
        return null;
    }
}
