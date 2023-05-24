package com.example.btl_web.service;

import com.example.btl_web.dto.CommentDto;
import com.example.btl_web.paging.Pageable;

import java.util.List;

public interface UserBlogService {
    List<CommentDto> findAll(Pageable pageable, CommentDto comment);
    boolean saveComment(CommentDto comment);
    boolean deleteComment(CommentDto commentDto);
    boolean likeThisBlog(Long blogId, Long userId);
    boolean removeLikeThisBlog(Long blogId, Long userId);
    boolean validComment(CommentDto comment, String[] error);
}