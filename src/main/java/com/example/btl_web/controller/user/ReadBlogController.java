package com.example.btl_web.controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.btl_web.constant.Constant.*;

import java.io.IOException;

@WebServlet(urlPatterns = User.READ_BLOG_PAGE)
public class ReadBlogController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogIdStr = req.getPathInfo().split("/")[1];

        RequestDispatcher rd = req.getRequestDispatcher(User.READ_BLOG_JSP);
        rd.forward(req, resp);
    }
}
