package com.example.btl_web.controller;

import com.example.btl_web.service.UserService;
import com.example.btl_web.service.impl.UserServiceimpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    private UserService userService = UserServiceimpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
        //req.setAttribute("message", "Tài khoản mật khẩu không chính xác!");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");

        if(userService.login(userName, passWord))
        {
            resp.sendRedirect(req.getContextPath() + "/");
        }
        else
        {
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            req.setAttribute("message", "Tài khoản mật khẩu không chính xác!");
            req.setAttribute("display_flex", "display__flex");
            req.setAttribute("message_type", "alert");
            rd.forward(req, resp);
        }
    }
}
