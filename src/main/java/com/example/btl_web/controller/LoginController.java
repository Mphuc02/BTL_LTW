package com.example.btl_web.controller;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.UserService;
import com.example.btl_web.service.impl.UserServiceimpl;
import com.example.btl_web.utils.SessionUtils;
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
        RequestDispatcher rd = req.getRequestDispatcher( Constant.LOGIN_JSP);

        String action = req.getParameter("action");
        if(action != null)
        {
            if(action.equals("not_login"))
            {
                req.setAttribute("message", "Bạn chưa đăng nhập!");
            }
            else if (action.equals("not_permission"))
            {
                req.setAttribute("message", "Bạn không có quyền truy cập địa chỉ này!");
            }
            else if(action.equals("logout"))
            {
                req.setAttribute("message", "Đăng xuất thành công!");
                SessionUtils.getInstance().removeValue(req, Constant.USER_MODEL);
            }
            req.setAttribute("display_flex", "display__flex");
            req.setAttribute("message_type", "alert");
        }

        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");

        UserDto userDto = userService.login(userName, passWord);

        if(userDto != null)
        {
            SessionUtils session = SessionUtils.getInstance();
            session.putValue(req, Constant.USER_MODEL, userDto);

            if(userDto.getRole().equals(Constant.USER))
            {
                resp.sendRedirect(req.getContextPath() + "/");
            }
            else if(userDto.getRole().equals(Constant.ADMIN))
            {
                resp.sendRedirect(req.getContextPath() + "/");
            }
        }
        else
        {
            RequestDispatcher rd = req.getRequestDispatcher(Constant.LOGIN_JSP);
            req.setAttribute("message", "Tài khoản mật khẩu không chính xác!");
            req.setAttribute("display_flex", "display__flex");
            req.setAttribute("message_type", "alert");
            rd.forward(req, resp);
        }
    }
}