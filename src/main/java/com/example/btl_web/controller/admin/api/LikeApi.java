package com.example.btl_web.controller.admin.api;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.UserBlogService;
import com.example.btl_web.service.impl.UserBlogServiceImpl;
import com.example.btl_web.utils.HttpUtils;
import com.example.btl_web.utils.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = User.USER_LIKE_API)
public class LikeApi extends HttpServlet {
    private UserBlogService userBlogService = UserBlogServiceImpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        BlogDto likedBlog = HttpUtils.of(req.getReader()).toModel(BlogDto.class);

        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
        boolean statusLiked = userBlogService.likeThisBlog(likedBlog.getBlogId(), user.getUserId());

        ObjectMapper mapper = new ObjectMapper();
        if(statusLiked)
        {
            resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("message", "")));
        }
        else
        {
            resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("errors", "Bạn phải đăng nhập thì mới có thể like bài viết này!")));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        BlogDto likedBlog = HttpUtils.of(req.getReader()).toModel(BlogDto.class);

        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
        boolean statusLiked = userBlogService.removeLikeThisBlog(likedBlog.getBlogId(), user.getUserId());

        ObjectMapper mapper = new ObjectMapper();
        if(statusLiked)
        {
            resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("message", "")));
        }
        else
        {
            resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("errors", "Bạn phải đăng nhập thì mới có thể like bài viết này!")));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
