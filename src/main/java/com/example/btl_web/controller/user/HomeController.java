package com.example.btl_web.controller.user;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.paging.PageRequest;
import com.example.btl_web.paging.Pageable;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.service.impl.BlogServiceImpl;
import com.example.btl_web.service.impl.CategoryServiceImpl;
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
    private CategoryService categoryService = CategoryServiceImpl.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyWord = request.getParameter("sortName");
        String categorySearch = request.getParameter("categorySearch");

        BlogDto blogApproved = new BlogDto();
        blogApproved.setTitle(keyWord);
        blogApproved.setStatus(1);

        long totalBlogs = blogService.countBlogs(blogApproved);
        Pageable pageable = new PageRequest(request.getParameterMap(), totalBlogs);

        List<BlogDto> blogDtos = blogService.getAllBlogs(pageable,blogApproved);
        List<CategoryDto> categoryDtos = categoryService.findAll(null, null);

        StringBuilder homeUrl = new StringBuilder(User.HOME_PAGE + "?" );
        if(categorySearch != null)
            homeUrl.append("categorySearch=" + categorySearch+"&");
        if(keyWord != null)
            homeUrl.append("sortName=" + keyWord + "&");
        homeUrl.append("page=");

        request.setAttribute("pageable", pageable);
        request.setAttribute("key", keyWord);
        request.setAttribute("listA", blogDtos);
        request.setAttribute("home", homeUrl.toString());
        request.setAttribute("categories", categoryDtos);
        request.setAttribute("category_search", categorySearch);

        UserDto user = (UserDto) SessionUtils.getInstance().getValue(request, Constant.USER_MODEL);
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
