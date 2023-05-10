package com.example.btl_web.controller.admin;

import com.example.btl_web.configuration.ServiceConfiguration;
import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.service.BlogService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = Admin.BLOGS_PAGE)
public class BlogController extends HttpServlet {
    private BlogService blogService = ServiceConfiguration.getBlogService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(Admin.BLOGS_JSP);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
