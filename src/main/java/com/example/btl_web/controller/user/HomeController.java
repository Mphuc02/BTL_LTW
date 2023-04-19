package com.example.btl_web.controller.user;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.paging.PageRequest;
import com.example.btl_web.paging.Pageable;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.service.impl.BlogServiceImpl;
import com.example.btl_web.utils.SessionUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.btl_web.constant.Constant.*;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeController extends HttpServlet {
    private BlogService blogService = BlogServiceImpl.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BlogDto blogApproved = new BlogDto();
        blogApproved.setStatus(1);

        long totalBlogs = blogService.countBlogs(blogApproved);
        Pageable pageable = new PageRequest(request.getParameterMap(), totalBlogs);
        request.setAttribute("pageable", pageable);

        UserDto user = (UserDto) SessionUtils.getInstance().getValue(request, Constant.USER_MODEL);

        List<BlogDto> blogDtos = blogService.getAllBlogs(pageable,blogApproved);
        request.setAttribute("listA", blogDtos);
        request.setAttribute("home", User.HOME_PAGE);
        if(user != null)
        {
            request.setAttribute(Constant.USER_MODEL, user);
            if(user.getRole().equals(Constant.ADMIN))
            {
                request.setAttribute("categories_page", Admin.CATEGORIES_PAGE);
                request.setAttribute("blogs_page", Admin.BLOGS_PAGE);
                request.setAttribute("users_page", Admin.USERS_PAGE);
            }
        }

        request.getRequestDispatcher(User.HOME_JSP).forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
