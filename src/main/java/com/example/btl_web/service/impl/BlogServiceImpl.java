package com.example.btl_web.service.impl;

import com.example.btl_web.dao.BlogDao;
import com.example.btl_web.dao.impl.BlogDaoImpl;
import com.example.btl_web.model.Blog;
import com.example.btl_web.service.BlogService;

import java.util.List;

public class BlogServiceImpl implements BlogService {
    private BlogDao blogDao = BlogDaoImpl.getInstance();
    private static BlogServiceImpl blogService;
    public static BlogServiceImpl getInstance()
    {
        if(blogService == null)
            blogService = new BlogServiceImpl();
        return blogService;
    }

    @Override
    public List<Blog> getAllBlogs() {
        String sql = "SELECT * FROM BLOGS";
        return blogDao.findAll(sql);
    }

    @Override
    public int getTotalBlogs() {
        String sql = "SELECT * FROM BLOGS";
        return blogDao.findAll(sql).size();
    }

    @Override
    public List<Blog> pagingBlogs(int index) {
        String sql = "SELECT * FROM BLOGS LIMIT 8 OFFSET ?";
        return blogDao.findAll(sql);
    }

    @Override
    public List<Blog> searchBlogsByName(String search) {
        String sql = "SELECT * FROM `blogs` WHERE `title` like ?";
        return blogDao.findAll(sql, search);
    }
}
