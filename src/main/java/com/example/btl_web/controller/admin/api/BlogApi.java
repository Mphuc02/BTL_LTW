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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;

@WebServlet(urlPatterns = Admin.BLOGS_API)
public class BlogApi extends HttpServlet {
    BlogService blogService = BlogServiceImpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        BlogDto blog = HttpUtils.of(req.getReader()).toModel(BlogDto.class);
        String[] errors = new String[4];
        boolean valid = blogService.validUpdateBlog(errors, blog);
        ObjectMapper mapper = new ObjectMapper();
        if(valid)
        {
            UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
            blog.setUser(user);

            Long blogId = blogService.update(blog);
            if(blogId != null)
            {
                String relativeWebPath = "/images/blog/";
                String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
                FileUtils.saveImageToServer(blog.getImageTitleData(), blogId, absoluteDiskPath);

                resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("messages", blogId)));
            }
            else
            {
                resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            }
        }
        else
        {
            resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("errors", errors)));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        BlogDto blog = HttpUtils.of(req.getReader()).toModel(BlogDto.class);
        String[] errors = new String[4];
        boolean valid = blogService.validUpdateBlog(errors, blog);
        ObjectMapper mapper = new ObjectMapper();
        if(valid)
        {
            UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
            blog.setUser(user);

            Long blogId = blogService.update(blog);
            if(blogId != null)
            {
                String relativeWebPath = "/images/blog/";
                String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
                FileUtils.saveImageToServer(blog.getImageTitleData(), blogId, absoluteDiskPath);

                resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("messages", blogId)));
            }
            else
            {
                resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            }
        }
        else
        {
            resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("errors", errors)));
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void solveApi(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        BlogDto blog = HttpUtils.of(req.getReader()).toModel(BlogDto.class);
        String[] errors = new String[4];

        String requestMethod = req.getMethod();
        boolean valid = false;
        if(requestMethod.equals(Request.POST_METHOD))
            valid = blogService.validCreateBlog(errors, blog);
        else if(requestMethod.equals(Request.PUT_METHOD) || requestMethod.equals(Request.DELETE_METHOD))
            valid = blogService.validUpdateBlog(errors, blog);

        ObjectMapper mapper = new ObjectMapper();

        if(valid)
        {
            UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
            blog.setUser(user);

            Long blogId = null;
                    blogService.save(blog);
            if(blogId != null) {
                String relativeWebPath = "/images/blog/";
                String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
                FileUtils.saveImageToServer(blog.getImageTitleData(), blogId, absoluteDiskPath);

                resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("messages", blogId)));
                return;
            }
        }
        resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("errors", errors)));
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
