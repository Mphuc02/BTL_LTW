package com.example.btl_web.controller;

import com.example.btl_web.configuration.ServiceConfiguration;
import com.example.btl_web.constant.Constant;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.service.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(value = Constant.STATISTICAL_PAGE)
public class StatisticalController extends HttpServlet {
    private BlogService blogService = ServiceConfiguration.getBlogService();
    private CategoryService categoryService = ServiceConfiguration.getCategoryService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<BlogDto> Like_Max = blogService.BlogsMaxLike(null,null);
        List<BlogDto> Comment_Max = blogService.BlogsMaxComment(null,null);
        List<BlogDto> New = blogService.BlogsNew(null,null);
        List<CategoryDto> Blog_Max = categoryService.CategoryMaxBlogs(null,null);

        request.setAttribute("Like",Like_Max);
        request.setAttribute("Cmt",Comment_Max);
        request.setAttribute("New", New);
        request.setAttribute("Cate",Blog_Max);

        request.getRequestDispatcher(Constant.STATISTICAL_JSP).forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
