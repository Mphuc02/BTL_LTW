package com.example.btl_web.service;

import com.example.btl_web.dto.CommentDto;

public interface UserBlogService {
    boolean likeThisBlog(Long blogId, Long userId);
    boolean removeLikeThisBlog(Long blogId, Long userId);
    boolean validComment(CommentDto comment, String error);
}