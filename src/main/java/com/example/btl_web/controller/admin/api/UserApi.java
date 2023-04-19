package com.example.btl_web.controller.admin.api;

import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.UserService;
import com.example.btl_web.service.impl.UserServiceimpl;
import com.example.btl_web.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import com.example.btl_web.constant.Constant.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = Admin.USER_API)
public class UserApi extends HttpServlet {
    private UserService userService = UserServiceimpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        UserDto user = HttpUtils.of(req.getReader()).toModel(UserDto.class);
        Long status = userService.saveUser(user);

        if(status != null)
        {
            resp.sendError(HttpServletResponse.SC_OK);
        }
        else
        {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        UserDto user = HttpUtils.of(req.getReader()).toModel(UserDto.class);
        Long status = userService.updateUser(user);

        if(status != null)
        {
            resp.sendError(HttpServletResponse.SC_OK);
        }
        else
        {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        UserDto user = HttpUtils.of(req.getReader()).toModel(UserDto.class);
        Long status = userService.updateUser(user);

        if(status != null)
        {
            resp.sendError(HttpServletResponse.SC_OK);
        }
        else
        {
            resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }
}
