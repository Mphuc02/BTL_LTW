package com.example.btl_web.controller.user;

import com.example.btl_web.configuration.ServiceConfiguration;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.service.impl.BlogServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.btl_web.constant.Constant.*;

import java.io.IOException;

@WebServlet(urlPatterns = User.READ_BLOG_PAGE)
public class ReadBlogController extends HttpServlet {
    private BlogService blogService = ServiceConfiguration.getBlogService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getPathInfo().split("/")[1];
        if(idStr!= null)
        {
            Long id = Long.parseLong(idStr);
            BlogDto blogDto = new BlogDto();
            blogDto.setBlogId(id);
            blogDto.setStatus(1);

            blogDto = blogService.getOne(blogDto);

            if(blogDto != null)
            {
                request.setAttribute("blog",blogDto);
                request.getRequestDispatcher(User.READ_BLOG_JSP).forward(request,response);
            }
            else
            {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        else
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
