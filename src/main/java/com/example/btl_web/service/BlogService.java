package com.example.btl_web.service;

import com.example.btl_web.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();
    int getTotalBlogs();
    List<Blog> pagingBlogs(int index);
    List<Blog> searchBlogsByName(String search);
}
