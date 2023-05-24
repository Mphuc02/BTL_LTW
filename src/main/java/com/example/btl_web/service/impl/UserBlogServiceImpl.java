package com.example.btl_web.service.impl;

import com.example.btl_web.configuration.ServiceConfiguration;
import com.example.btl_web.dao.CommentDao;
import com.example.btl_web.dao.UserDao;
import com.example.btl_web.dao.impl.CommentDaoImpl;
import com.example.btl_web.dao.impl.UserDaoImpl;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.CommentDto;
import com.example.btl_web.dto.UserDto;
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
    private UserService userService = ServiceConfiguration.getUserService();
    @Override
    public List<CommentDto> findAll(Pageable pageable, CommentDto comment) {
        pageable.setLimit(1000);
        StringBuilder sql = new StringBuilder("Select c.* from comments c where c.blog_id = " + comment.getBlogComment());
        sql.append(addAndClause(pageable, comment));
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
        String sql = "Insert into Comments (content, blog_id, user_id, created_at, status) values (?, ?, ?, ?, 1)";
        Long saveCommentStatus = commentDao.update(sql, comment.getContent(), comment.getBlogComment(), comment.getUserComment().getUserId(), timeStamp.getTime());
        if(saveCommentStatus != null)
        {
            return userService.updateLastAction(comment.getUserComment());
        }
        return false;
    }

    @Override
    public boolean deleteComment(CommentDto commentDto) {
        String sql = "Delete from Comments where comment_id = ?";
        Long deleteStatus = commentDao.update(sql, commentDto.getCommentId());
        return deleteStatus == null ? false : true;
    }

    @Override
    public boolean likeThisBlog(Long blogId, Long userId) {
        if(blogId == null || userId == null)
            return false;
        String sql = "Insert into Liked (user_id, blog_id) values (" + userId + ", " + blogId + ")";
        Long status = commentDao.update(sql);

        //Lưu hoạt động gần đây nhất của user
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userService.updateLastAction(userDto);

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
    public boolean validComment(CommentDto comment, String[] error) {
        if(!userCanDoAction(error, comment.getUserComment().getUserId()))
            return false;

        if(comment.getBlogComment() == null)
        {
            error[0] = "Bài viết không tồn tại!";
            return false;
        }

        //Kiểm tra xem bài viết được comment có tồn tại hay không
        BlogDto blogDto = new BlogDto();
        blogDto.setBlogId(comment.getBlogComment());
        blogDto.setStatus(1);
        BlogService blogService = new BlogServiceImpl();
        List<BlogDto> blogs = blogService.getAllBlogs(null, blogDto);
        if(blogs == null || blogs.isEmpty())
        {
            error[0] = "Bài viết không tồn tại!";
            return false;
        }

        if(comment.getContent() == null || comment.getContent().isEmpty())
        {
            error[0] = "Nội dung không được để trống!";
            return false;
        }
        return true;
    }
    private boolean userCanDoAction(String errors[], Long userId)
    {
        String timeValid = userService.checkLastAction(userId);
        if(timeValid != null)
        {
            errors[0] = timeValid;
            return false;
        }
        return true;
    }

    private StringBuilder addAndClause(Pageable pageable, CommentDto commentDto)
    {
        StringBuilder sb = new StringBuilder();
        if(commentDto != null)
        {

        }

        if(pageable != null)
        {
            sb.append(pageable.addPagingation());
        }

        return sb;
    }
}
