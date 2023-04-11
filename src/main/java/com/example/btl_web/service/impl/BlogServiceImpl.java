package com.example.btl_web.service.impl;

import com.example.btl_web.dao.BlogDao;
import com.example.btl_web.dao.impl.BlogDaoImpl;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.model.Blog;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.utils.ConvertUtils;

import java.util.ArrayList;
import java.util.Date;
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
    public List<BlogDto> getAllBlogs() {
        String sql = "SELECT * FROM BLOGS";
        List<Blog> blogs = blogDao.findAll(sql);

        List<BlogDto> dtos = new ArrayList<>();

        for(Blog blog: blogs)
        {
            dtos.add(ConvertUtils.convertEntityToDto(blog, BlogDto.class));
        }

        return dtos;
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

    @Override
    public Boolean save(Blog blog) {
        Date timeStamp = new Date();
        blog.setCreateAt(timeStamp.getTime());
        String sql = "INSERT INTO BLOGS (content, created_at, image_title, title, user_id)";
        return blogDao.save(sql,blog.getContent(), blog.getCreateAt(), blog.getImageTitle(), blog.getUserBlog().getUserId());
    }
}
