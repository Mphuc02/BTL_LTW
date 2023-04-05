package com.example.btl_web.filter;

import com.example.btl_web.constant.Constant;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.utils.SesstionUtils;
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

        if(url.contains("admin"))
        {
            UserDto user = (UserDto) SesstionUtils.getInstance().getValue(request,Constant.USER_MODEL);

            if(user.getRole().equals(Constant.ADMIN))
            {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else
            {
                if(user == null)
                {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                else
                {
                    response.sendRedirect(request.getContextPath() + "/login?action=not_login");
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
