package com.example.btl_web.controller;

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
import com.example.btl_web.constant.Constant.*;

import java.io.IOException;

@WebServlet(value = "/registration")
public class RegistrationController extends HttpServlet {
    private UserService userService = UserServiceimpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(Constant.REGISTRAION_JSP);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        String passWord_2 = req.getParameter("passWord-2");
        String email = req.getParameter("email");

        int errorSignUp = userService.signUp(userName, passWord, passWord_2, email);

        if(errorSignUp == -1)
        {
            UserDto userDto = new UserDto();
            userDto.setUserName(userName);
            userDto.setPassWord(passWord);
            userDto.setEmail(email);

            userService.saveUser(userDto);
            resp.sendRedirect(req.getContextPath() + User.LOGIN_PAGE);
        }
        else
        {
            if(errorSignUp == 1)
            {
                req.setAttribute("bug1", "Tên đăng nhập đã tồn tại!");
            }
            else if(errorSignUp == 2)
            {
                req.setAttribute("bug2", "Mật khẩu phải dài ít nhất 6 ký tự!");
            }
            else if(errorSignUp == 3)
            {
                req.setAttribute("bug3", "Mật khẩu xác nhận không khớp!");
            }
            else if(errorSignUp == 4)
            {
                req.setAttribute("bug4", "Email không đúng định dạng!");
            }
            RequestDispatcher rd = req.getRequestDispatcher(Constant.REGISTRAION_JSP);

            rd.forward(req, resp);
        }
    }
}
