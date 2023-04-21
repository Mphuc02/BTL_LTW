package com.example.btl_web.controller.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

@WebServlet("/test")
public class test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("test.jsp");

        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        req.setCharacterEncoding("UTF-8");

        String[] errors = {"Tài khoản này đã được tạo!", "Mật khẩu phải có ít nhất 8 ký tự"};

        ObjectMapper mapper = new ObjectMapper();

        resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("errors", errors)));
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}
