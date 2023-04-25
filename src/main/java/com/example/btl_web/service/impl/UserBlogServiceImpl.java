package com.example.btl_web.service.impl;

import com.example.btl_web.dao.CommentDao;
import com.example.btl_web.dao.UserDao;
import com.example.btl_web.dao.impl.CommentDaoImpl;
import com.example.btl_web.dao.impl.UserDaoImpl;
import com.example.btl_web.dto.CommentDto;
import com.example.btl_web.service.UserBlogService;


public class UserBlogServiceImpl implements UserBlogService {
    private CommentDao commentDao = CommentDaoImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();
    private static UserBlogServiceImpl userBogService;
    public static UserBlogServiceImpl getInstance()
    {
        if(userBogService == null)
            userBogService = new UserBlogServiceImpl();
        return userBogService;
    }

    @Override
    public boolean likeThisBlog(Long blogId, Long userId) {
        if(blogId == null || userId == null)
            return false;
        String sql = "Insert into Liked (user_id, blog_id) values (" + userId + ", " + blogId + ")";
        Long status = commentDao.update(sql);
        return status != null;
    }

    @Override
    public boolean removeLikeThisBlog(Long blogId, Long userId) {
        if(blogId == null || userId == null)
            return false;
        String sql = "Delete from liked where blog_id = " + blogId + " and user_id = " + userId;
        Long status = commentDao.update(sql);
        return status != null;
    }

    @Override
    public boolean validComment(CommentDto comment, String error) {
        if(comment.getUserComment() == null)
        {
            error = "Bạn chưa đăng nhập!";
            return false;
        }
        if(comment.getContent() == null || comment.getContent().isEmpty())
        {
            error = "Nội dung không được để trống!";
            return false;
        }
        if(comment.getBlogComment() == null)
        {
            error = "Không rõ bài viết được comment!";
            return false;
        }
        return true;
    }

}
