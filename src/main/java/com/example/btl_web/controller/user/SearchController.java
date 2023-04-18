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

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    private BlogService blogService = BlogServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyWord = request.getParameter("keyword");

        BlogDto blogDto = new BlogDto();
        if(keyWord != null)
        {
            blogDto.setTitle(keyWord);
            blogDto.setContent(keyWord);
        }
        long totalBlogs = blogService.countBlogs(blogDto);
        Pageable pageable = new PageRequest(request.getParameterMap(), totalBlogs);

        List<BlogDto> dtos = blogService.getAllBlogs(pageable, blogDto);
        request.setAttribute("listA", dtos);
        request.setAttribute("pageable", pageable);
        request.setAttribute("key", keyWord);

        UserDto user = (UserDto) SessionUtils.getInstance().getValue(request, Constant.USER_MODEL);
        if(user != null)
        {
            request.setAttribute(Constant.USER_MODEL, user);
            if(user.getRole().equals(Constant.ADMIN))
            {
                request.setAttribute("categories_page", Constant.Admin.CATEGORIES_PAGE);
                request.setAttribute("blogs_page", Constant.Admin.BLOGS_PAGE);
                request.setAttribute("users_page", Constant.Admin.USERS_PAGE);
            }
        }

        request.getRequestDispatcher(Constant.User.SEARCH_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
