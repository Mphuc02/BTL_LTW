package com.example.btl_web.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.btl_web.constant.Constant.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin-home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String urlJsp = "";
        if(page == null || page.equals(Admin.CATEGORIES_PAGE))
        {
            urlJsp = Admin.CATEGORIES_JSP;
        }
        else if(page.equals(Admin.BLOGS_PAGE))
        {
            urlJsp = Admin.BLOGS_JSP;
        }
        else if(page.equals(Admin.USERS_PAGE))
        {
            urlJsp = Admin.USERS_JSP;
        }
        else if(page.equals(Admin.STATISTIC_PAGE))
        {
            urlJsp = Admin.STATISTIC_JSP;
        }

        req.setAttribute("blogs", Admin.BLOGS_PAGE);
        req.setAttribute("categories", Admin.CATEGORIES_PAGE);
        req.setAttribute("users", Admin.USERS_PAGE);

        RequestDispatcher rd = req.getRequestDispatcher(urlJsp);
        rd.forward(req, resp);
    }
}
