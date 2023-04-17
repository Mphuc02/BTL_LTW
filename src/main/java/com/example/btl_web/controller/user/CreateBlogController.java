package com.example.btl_web.controller.user;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.paging.PageRequest;
import com.example.btl_web.paging.Pageable;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.service.impl.CategoryServiceImpl;
import com.example.btl_web.utils.SessionUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = User.CREATE_BLOG_PAGE)
public class CreateBlogController extends HttpServlet {
    private CategoryService categoryService = CategoryServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
        if(user == null)
        {
            resp.sendRedirect(User.LOGIN_PAGE+ "?" + User.ACTION + "=" + User.ACTION_NOT_LOGIN);
        }
        else
        {
            BlogDto blogDto = new BlogDto();

            List<CategoryDto> categories = categoryService.findAll(null, null);

            req.setAttribute("blog", blogDto);
            req.setAttribute("categories", categories);

            RequestDispatcher rd = req.getRequestDispatcher(User.CREATE_BLOG_JSP);

            rd.forward(req, resp);
        }
    }
}
