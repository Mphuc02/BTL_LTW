package com.example.btl_web.service;

import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.paging.Pageable;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface BlogService {
    List<BlogDto> getAllBlogs(Pageable pageable, BlogDto dto);
    BlogDto getOne(BlogDto searchBlog);
    long countBlogs(BlogDto blogDto);
    Long save(BlogDto blog);
    Long update(BlogDto blog);
    boolean validCreateBlog(HttpServletRequest req, BlogDto blog);
    boolean validUpdateBlog(HttpServletRequest req, BlogDto blog, Long userId);
    boolean checkUserLikedBlog(BlogDto blog, Long userId);
    void addPTagContent(BlogDto blog);
    void removePTagContent(BlogDto blog);
}
