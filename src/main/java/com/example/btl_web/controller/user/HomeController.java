package com.example.btl_web.controller.user;

import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.paging.PageRequest;
import com.example.btl_web.paging.Pageable;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.service.impl.BlogServiceImpl;
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
        long totalBlogs = blogService.countBlogs(null);
        Pageable pageable = new PageRequest(request.getParameterMap(), totalBlogs);
        request.setAttribute("pageable", pageable);

        List<BlogDto> blogDtos = blogService.getAllBlogs(pageable,null);
        request.setAttribute("listA", blogDtos);
        request.setAttribute("home", User.HOME_PAGE);

        request.getRequestDispatcher(User.HOME_JSP).forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
