package com.example.btl_web.service.impl;

import com.example.btl_web.dao.CategoryDao;
import com.example.btl_web.dao.impl.CategoryDaoImpl;
import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.model.Category;
import com.example.btl_web.paging.Pageable;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.utils.ConvertUtils;

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
    public List<CategoryDto> findAll(Pageable pageable, CategoryDto dto) {
        StringBuilder sql = new StringBuilder("SELECT * FROM CATEGORIES WHERE (1 = 1)");

        sql.append(addAndClause(pageable, dto));

        List<Category> categories = categoryDao.select(sql.toString());
        List<CategoryDto> dtos = new ArrayList<>();
        for(Category category: categories)
        {
            dtos.add(ConvertUtils.convertEntityToDto(category, CategoryDto.class));
        }

        return dtos;
    }

    @Override
    public List<CategoryDto> findAllCategoryOfBlog(Long blogCategoryId, Integer status) {
        StringBuilder sql = new StringBuilder("Select c.category_id, c.name from categories c, blogs_categories b_c, blogs b where " +
                                              "b.blog_id = ? and " +
                                              "c.status = 1 and " +
                                              "b_c.category_id = c.category_id and " +
                                              "b_c.blog_id = b.blog_id");
        List<Category> categories = categoryDao.selectDisplay(sql.toString(), blogCategoryId);
        if(categories == null)
            return null;
        List<CategoryDto> result = new ArrayList<>();
        categories.forEach( category -> {result.add(ConvertUtils.convertEntityToDto(category, CategoryDto.class));});

        return result;
    }

    @Override
    public CategoryDto findOneBy(CategoryDto dto) {
        List<CategoryDto> dtos = findAll(null, dto);

        return dtos.isEmpty() ? null: dtos.get(0);
    }

    @Override
    public long countCategories() {
        String sql = "Select count(category_id) from categories";
        return categoryDao.count(sql);
    }

    @Override
    public Long save(CategoryDto categoryDto) {
        if(!checkValidCategory(categoryDto))
            return null;

        Date timeStamp = new Date();
        StringBuilder sql = new StringBuilder("INSERT INTO CATEGORIES (name, user_id, created_at, status) values (?, ?, ?, 1)");

        return categoryDao.update(sql.toString(),categoryDto.getName(), categoryDto.getUserId(), timeStamp.getTime());
    }

    @Override
    public Long update(CategoryDto categoryDto) {
        StringBuilder sql = new StringBuilder("UPDATE CATEGORIES SET category_id = ?");
        sql.append(addClauseUpdate(categoryDto));

        return categoryDao.update(sql.toString(), categoryDto.getCategoryId());
    }

    @Override
    public Long saveCategoriesOfBlog(Long blogId, List<CategoryDto> categories) {
        String sql = "INSERT INTO blogs_categories (blog_id, category_id) values (?, ?)";

        for(CategoryDto category: categories)
        {
            Long status = categoryDao.update(sql, blogId ,category.getCategoryId());
            if(status == null)
                return null;
        }
        return 1L;
    }

    @Override
    public Long delete(Long categoryId) {
        String sql = "DELETE FROM CATEGORIES WHERE category_id = ?";
        return categoryDao.update(sql, categoryId);
    }

    @Override
    public boolean validCategoryCreate(CategoryDto category, String errors[]) {
        if(category.getName().isEmpty())
        {
            errors[0] = "Tên thể loại không được để trống!";
            return false;
        }
        return true;
    }

    @Override
    public boolean validCategoryUpdate(CategoryDto category, String[] error)
    {
        //Kiểm tra xem thể loại này có tồn tại không
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getCategoryId());
        List<CategoryDto> categories = findAll(null, dto);
        if(categories == null || categories.isEmpty())
            return false;
        return true;
    }

    private StringBuilder addAndClause(Pageable pageable,CategoryDto categoryDto)
    {
        StringBuilder sb = new StringBuilder();
        if(categoryDto != null)
        {
            if(categoryDto.getCategoryId() != null)
                sb.append(" AND category_id = " + categoryDto.getCategoryId());
            if(categoryDto.getName() != null)
                sb.append(" AND NAME lower(like) lower(%" + categoryDto.getName() + "%)");
//            if(categoryDto.getCreatedAt() != null)
//                sb.append(" AND created_at = " + categoryDto.getCreatedAt());
            if(categoryDto.getUserId() != null)
                sb.append(" AND user_id = " + categoryDto.getUserId());
        }

        if(pageable != null)
            sb.append(pageable.addPagingation());

        return sb;
    }

    private StringBuilder addClauseUpdate(CategoryDto dto)
    {
        StringBuilder sb = new StringBuilder();
        Long id = dto.getCategoryId();
        String name = dto.getName();
        Long userId = dto.getUserId();

        String createAt = dto.getCreatedAt();

        Integer status = dto.getStatus();

        if(name != null)
            sb.append(", name = '" + name + "'");
        if(userId != null)
            sb.append(", user_id = " + userId);
//        if(createAt != null)
//            sb.append(", created_at = ?");
        if(status != null)
            sb.append(", status = " + status);

        sb.append(" WHERE category_id = " + id);

        return sb;
    }

    private boolean checkValidCategory(CategoryDto categoryDto)
    {
        String name = categoryDto.getName();

        if(name == null || name.equals(""))
            return false;
        return true;
    }
}
