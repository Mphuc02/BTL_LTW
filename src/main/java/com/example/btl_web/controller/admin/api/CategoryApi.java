package com.example.btl_web.controller.admin.api;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.service.impl.CategoryServiceImpl;
import com.example.btl_web.utils.HttpUtils;
import com.example.btl_web.utils.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import com.example.btl_web.constant.Constant.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = Admin.CATEGORY_API)
public class CategoryApi extends HttpServlet {
    private CategoryService categoryService = CategoryServiceImpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        CategoryDto category = HttpUtils.of(req.getReader()).toModel(CategoryDto.class);
        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
        category.setUserId(user.getUserId());

        boolean status = categoryService.save(category);
        ObjectMapper mapper = new ObjectMapper();
        if(status)
        {
            mapper.writeValue(resp.getOutputStream(), true);
        }
        else
        {
            mapper.writeValue(resp.getOutputStream(), false);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        CategoryDto category = HttpUtils.of(req.getReader()).toModel(CategoryDto.class);

        boolean status = categoryService.update(category);
        ObjectMapper mapper = new ObjectMapper();
        if(status)
        {
            mapper.writeValue(resp.getOutputStream(), true);
        }
        else
        {
            mapper.writeValue(resp.getOutputStream(), false);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        CategoryDto category = HttpUtils.of(req.getReader()).toModel(CategoryDto.class);

        boolean status = categoryService.hidden(category.getCategoryId(), category.getStatus());
        ObjectMapper mapper = new ObjectMapper();
        if(status)
        {
            mapper.writeValue(resp.getOutputStream(), true);
        }
        else
        {
            mapper.writeValue(resp.getOutputStream(), false);
        }
    }
}
