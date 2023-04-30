package com.example.btl_web.controller.user;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.UserService;
import com.example.btl_web.service.impl.UserServiceimpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = Constant.User.USER_DETAIL_PAGE)
public class UserDetailController extends HttpServlet {
    private UserService userService = UserServiceimpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdStr = req.getPathInfo().split("/")[1];
        if(userIdStr == null)
        {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Long userId = Long.parseLong(userIdStr);
        UserDto searchDto = userService.findOneById(userId);
        if(searchDto == null)
        {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        req.setAttribute(Constant.USER, searchDto);
        RequestDispatcher rd = req.getRequestDispatcher(Constant.User.USER_DETAIL_JSP);
        rd.forward(req, resp);
    }
}
