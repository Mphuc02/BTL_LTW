package com.example.btl_web.filter;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.utils.SessionUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURI();

        UserDto user = (UserDto) SessionUtils.getInstance().getValue(request,Constant.USER_MODEL);
        request.setAttribute(Constant.USER_MODEL, user);

        if(url.contains("admin"))
        {
            if(user == null)
            {
                response.sendRedirect(request.getContextPath() + "/login?action=not_login");
            }
            else
            {
                if(user.getRole() >= Role.MOD)
                {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                else
                {
                    response.sendRedirect(request.getContextPath() + "/login?action=not_permission");
                }
            }
        }
        else if(url.startsWith("/api"))
        {
            if(url.equals(Constant.User.USER_CREATE_API) && user == null)
            {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else
            {
                if(user == null)
                {
                    response.sendRedirect(request.getContextPath() + "/login?action=not_login");
                }
                else
                {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }
        else
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
