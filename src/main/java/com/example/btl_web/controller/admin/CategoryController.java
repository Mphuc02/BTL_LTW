package com.example.btl_web.controller.admin;

import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.paging.PageRequest;
import com.example.btl_web.paging.Pageable;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.service.impl.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.btl_web.constant.Constant.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = Admin.CATEGORIES_PAGE)
public class CategoryController extends HttpServlet {
    private CategoryService categoryService = CategoryServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long totalCategories = categoryService.countCategories();
        Pageable pageable = new PageRequest(req.getParameterMap(), totalCategories );
        req.setAttribute("pageable", pageable);

        List<CategoryDto> categoryDtos = categoryService.findAll(pageable, null);
        req.setAttribute("list", categoryDtos);

        req.setAttribute("blogs", Admin.BLOGS_PAGE);
        req.setAttribute("users", Admin.USERS_PAGE);
        req.setAttribute("categories", Admin.CATEGORIES_PAGE);

        RequestDispatcher rd = req.getRequestDispatcher(Admin.CATEGORIES_JSP);
        rd.forward(req, resp);
    }
}
