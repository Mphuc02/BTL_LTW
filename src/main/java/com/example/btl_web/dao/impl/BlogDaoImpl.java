package com.example.btl_web.dao.impl;

import com.example.btl_web.dao.BlogDao;
import com.example.btl_web.mapper.impl.BlogMapperImpl;
import com.example.btl_web.model.Blog;

import java.util.List;

public class BlogDaoImpl extends GeneralDaoImpl<Blog> implements BlogDao {
    private static BlogDaoImpl blogDao;
    public static BlogDaoImpl getInstance()
    {
        if(blogDao == null)
            blogDao = new BlogDaoImpl();
        return blogDao;
    }
    @Override
    public List<Blog> findAll(String sql, Object... parameters) {
        return select(sql, new BlogMapperImpl(), parameters);
    }

    @Override
    public List<Blog> findBlogByName(String sql, Object... parameters) {
        return select(sql, new BlogMapperImpl(), parameters);
    }
}
