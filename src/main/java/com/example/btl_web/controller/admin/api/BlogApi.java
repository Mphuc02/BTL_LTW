package com.example.btl_web.controller.admin.api;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.model.Blog;
import com.example.btl_web.model.User;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.service.impl.BlogServiceImpl;
import com.example.btl_web.utils.HttpUtils;
import com.example.btl_web.utils.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/api-blog")
public class BlogApi extends HttpServlet {
    BlogService blogService = BlogServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        Blog blog = HttpUtils.of(req.getReader()).toModel(Blog.class);
        User user = (User) SessionUtils.getInstance().getValue(req,Constant.USER_MODEL);
        blog.setUserBlog(user);
        blogService.save(blog);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(), true);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
