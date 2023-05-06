package com.example.btl_web.controller.admin.api;

import com.example.btl_web.configuration.ServiceConfiguration;
import com.example.btl_web.constant.Constant;
import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.utils.SessionUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = Admin.BLOGS_API)
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15  // 15 MB
)
public class BlogApi extends HttpServlet {
    private BlogService blogService = ServiceConfiguration.getBlogService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
    {
        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);

        BlogDto createBlog = new BlogDto(req);

        String requestMethod = req.getParameter("_method");
        boolean valid = false;
        if(requestMethod.equals(Request.POST_METHOD))
        {
            createBlog.setUser(user);
            valid = blogService.validCreateBlog(req, createBlog);
        }
        else if(requestMethod.equals(Request.PUT_METHOD))
            valid = blogService.validUpdateBlog(req, createBlog, user.getUserId());

        if(valid)
        {
            Long blogId = blogService.save(createBlog);
            if(blogId != null)
            {
                req.setAttribute("success", "Tạo bài viết thành công, vui lòng đợi quản trị viên xét duyệt!");
                req.setAttribute("status", "notice");
            }
            //Todo: Thực hiện Dispatcher lại trang này và thông báo thành công
        }
        else
            req.setAttribute("status", "alert");
        //Todo: Thực hiện Dispatcher lại trang này và hiển thị các lỗi
        RequestDispatcher rd = req.getRequestDispatcher(User.CREATE_BLOG_JSP);
        rd.forward(req, resp);
    }
}