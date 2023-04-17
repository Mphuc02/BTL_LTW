package com.example.btl_web.service;

import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.model.Blog;
import com.example.btl_web.paging.Pageable;

import java.util.List;

public interface BlogService {
    List<BlogDto> getAllBlogs(Pageable pageable, BlogDto dto);
    long countBlogs(BlogDto blogDto);
    Long save(BlogDto blog);
}
