package com.example.btl_web.service.impl;

import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.dao.CategoryDao;
import com.example.btl_web.dao.impl.CategoryDaoImpl;
import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.model.Category;
import com.example.btl_web.paging.Pageable;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.utils.ConvertUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = CategoryDaoImpl.getInstance();
    private static CategoryServiceImpl categoryService;
    public static CategoryServiceImpl getInstance()
    {
        if(categoryService == null)
            categoryService = new CategoryServiceImpl();
        return categoryService;
    }
    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM CATEGORIES");

        String sortName = pageable.getSortName();
        String sortBy = pageable.getSortBy();
        if(sortName != null && sortBy != null)
            sql.append(" ORDER BY " + sortName + " " + sortBy) ;

        Integer offset = pageable.getOffset();
        Integer limit = pageable.getLimit();
        if(offset != null && limit != null)
            sql.append(" LIMIT " + offset + "," + limit);

        List<Category> categories = categoryDao.select(sql.toString());
        List<CategoryDto> dtos = new ArrayList<>();
        for(Category category: categories)
        {
            dtos.add(ConvertUtils.convertEntityToDto(category, CategoryDto.class));
        }

        return dtos;
    }

    @Override
    public CategoryDto findOneById(Long id) {
        String sql = "SELECT * FROM CATEGORIES WHERE category_id = ?";
        List<Category> categories = categoryDao.select(sql, id);

        List<CategoryDto> dtos = ConvertUtils.convertListEntitiesToDtos(categories, CategoryDto.class);

        return dtos.isEmpty() ? null : dtos.get(0);
    }

    @Override
    public boolean save(CategoryDto categoryDto) {
        Date timeStamp = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(Dto.DATE_FORMAT);
        categoryDto.setCreatedAt(sdf.format(timeStamp));
        StringBuilder sql = new StringBuilder("INSERT INTO CATEGORIES (name, user_id, created_at) values (?, ?, ?)");

        Category category = ConvertUtils.convertDtoToEntity(categoryDto, Category.class);

        return categoryDao.update(sql.toString(),category.getName(), category.getUserId(), category.getCreatedAt());
    }

    @Override
    public boolean update(CategoryDto categoryDto) {
        String sql = "UPDATE CATEGORIES SET name = ?, user_id = ?, created_at = ? where category_id = ?";

        Category category = ConvertUtils.convertDtoToEntity(categoryDto, Category.class);

        return categoryDao.update(sql, categoryDto.getName(), category.getUserId(), category.getCreatedAt(), category.getCategoryId());
    }

    @Override
    public boolean delete(Long categoryId) {
        return false;
    }

    private StringBuilder addAndClause(CategoryDto categoryDto)
    {
        StringBuilder sb = new StringBuilder();
        if(categoryDto.getCategoryId() != null)
            sb.append(" AND category_id = ?");
        if(categoryDto.getName() != null)
            sb.append(" AND NAME = ?");
        if(categoryDto.getCreatedAt() != null)
            sb.append(" AND created_at = ?");
        if(categoryDto.getUserId() != null)
            sb.append(" AND user_id = ?");

        return sb;
    }
}
