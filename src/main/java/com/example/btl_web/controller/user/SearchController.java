package com.example.btl_web.controller.user;

import com.example.btl_web.constant.Constant;
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

        request.getRequestDispatcher(Constant.User.SEARCH_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
