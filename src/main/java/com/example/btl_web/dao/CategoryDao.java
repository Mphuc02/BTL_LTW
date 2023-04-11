package com.example.btl_web.dao;
import com.example.btl_web.model.Category;

import java.util.List;

public interface CategoryDao{
    List<Category> select(String sql, Object... parameters);
    boolean update(String sql, Object... parameters);
}
