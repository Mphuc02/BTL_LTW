package com.example.btl_web.service;

import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.model.Blog;

import java.util.List;

public interface BlogService {
    List<BlogDto> getAllBlogs();
    int getTotalBlogs();
    List<Blog> pagingBlogs(int index);
    List<Blog> searchBlogsByName(String search);
    Boolean save(Blog blog);
}
