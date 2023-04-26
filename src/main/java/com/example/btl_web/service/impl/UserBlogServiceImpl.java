package com.example.btl_web.service.impl;

import com.example.btl_web.dao.CommentDao;
import com.example.btl_web.dao.UserDao;
import com.example.btl_web.dao.impl.CommentDaoImpl;
import com.example.btl_web.dao.impl.UserDaoImpl;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.CommentDto;
import com.example.btl_web.model.Comment;
import com.example.btl_web.paging.Pageable;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.service.UserBlogService;
import com.example.btl_web.service.UserService;
import com.example.btl_web.utils.ConvertUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserBlogServiceImpl implements UserBlogService {
    private CommentDao commentDao = CommentDaoImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();
    private BlogService blogService = BlogServiceImpl.getInstance();
    private UserService userService = UserServiceimpl.getInstance();
    private static UserBlogServiceImpl userBogService;
    public static UserBlogServiceImpl getInstance()
    {
        if(userBogService == null)
            userBogService = new UserBlogServiceImpl();
        return userBogService;
    }

    @Override
    public List<CommentDto> findAll(Pageable pageable, CommentDto comment) {
        StringBuilder sql = new StringBuilder("Select c.* from comments c, users u, blogs b where c.user_id = " + comment.getUserComment().getUserId() + " AND c.blog_id = " + comment.getBlogComment());
        List<Comment> comments = commentDao.findAll(sql.toString());
        List<CommentDto> result = new ArrayList<>();
        comments.forEach( e -> {
            result.add(ConvertUtils.convertEntityToDto(e, CommentDto.class));
        });

        return result;
    }

    @Override
    public boolean saveComment(CommentDto comment) {
        Date timeStamp = new Date();
        StringBuilder sql = new StringBuilder("Insert into Comments (content, blog_id, user_id, created_at ,status) values(1");
        sql.append(comment.getContent() + ", ");
        sql.append(comment.getBlogComment() + ", ");
        sql.append(comment.getUserComment().getUserId() + ", ");
        sql.append(timeStamp.getTime() + ", 1)");
        System.out.println(sql.toString());
        Long saveCommentStatus = commentDao.update(sql.toString(), comment.getContent(), comment.getBlogComment(), comment.getUserComment().getUserId(), timeStamp.getTime());
        if(saveCommentStatus != null)
        {
            return userService.updateLastAction(comment.getUserComment());
        }
        return false;
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
        Date timeAction = new Date();
        Long timeActionLong = timeAction.getTime();
        Long timeLastAction = comment.getUserComment().getLastAction();
        if(timeActionLong - timeLastAction < 1000 * 60)
        {
            error = "Bạn thao tác quá nhanh, vui lòng thử lại sau " + (timeActionLong - timeLastAction) / 1000;
            return false;
        }

        if(comment.getBlogComment() == null)
        {
            error = "Bài viết không tồn tại!";
            return false;
        }

        //Kiểm tra xem bài viết được comment có tồn tại hay không
        BlogDto blogDto = new BlogDto();
        blogDto.setBlogId(comment.getBlogComment());
        blogDto.setStatus(1);
        List<BlogDto> blogs = blogService.getAllBlogs(null, blogDto);
        if(blogs == null || blogs.isEmpty())
        {
            error = "Bài viết không tồn tại!";
            return false;
        }

        if(comment.getContent() == null || comment.getContent().isEmpty())
        {
            error = "Nội dung không được để trống!";
            return false;
        }
        return true;
    }

}
