package com.example.btl_web.controller.admin.api;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.dto.CommentDto;
import com.example.btl_web.service.UserBlogService;
import com.example.btl_web.service.impl.UserBlogServiceImpl;
import com.example.btl_web.utils.HttpUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = Constant.User.USER_COMMENT_API)
public class CommentApi extends HttpServlet {
    private UserBlogService userBlogService = UserBlogServiceImpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        CommentDto comment = HttpUtils.of(req.getReader()).toModel(CommentDto.class);
        String errors = null;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
