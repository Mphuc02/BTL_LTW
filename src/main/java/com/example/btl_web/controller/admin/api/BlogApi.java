package com.example.btl_web.controller.admin.api;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.service.impl.BlogServiceImpl;
import com.example.btl_web.utils.FileUtils;
import com.example.btl_web.utils.HttpUtils;
import com.example.btl_web.utils.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = Admin.BLOGS_API)
public class BlogApi extends HttpServlet {
    BlogService blogService = BlogServiceImpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        BlogDto blog = HttpUtils.of(req.getReader()).toModel(BlogDto.class);
        System.out.println(blog.getImageTitleData());
        String[] errors = new String[4];
        boolean valid = blogService.validateBlog(errors, blog);

        if(valid)
        {
            UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
            blog.setUser(user);

            Long blogId = blogService.save(blog);
            if(blogId != null)
            {
                String relativeWebPath = "/images/blog/";
                String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
                FileUtils.saveImageToServer(blog.getImageTitleData(), blogId, absoluteDiskPath);

                resp.sendError(HttpServletResponse.SC_OK);
            }
            else
            {
                resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            }
        }
        else
        {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
        if(!user.getRole().equals(Constant.ADMIN))
        {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        BlogDto blog = HttpUtils.of(req.getReader()).toModel(BlogDto.class);
        Long blogId = blogService.update(blog);

        if(blogId != null)
        {
            resp.sendError(HttpServletResponse.SC_OK);
        }
        else
        {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
        if(!user.getRole().equals(Constant.ADMIN))
        {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        BlogDto blog = HttpUtils.of(req.getReader()).toModel(BlogDto.class);
        Long blogId = blogService.update(blog);

        if(blogId != null)
        {
            resp.sendError(HttpServletResponse.SC_OK);
        }
        else
        {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }
}
