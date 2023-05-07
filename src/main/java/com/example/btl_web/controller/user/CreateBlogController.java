package com.example.btl_web.controller.user;

import com.example.btl_web.configuration.ServiceConfiguration;
import com.example.btl_web.constant.Constant;
import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.CategoryDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.utils.SessionUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {User.CREATE_BLOG_PAGE, User.UPDATE_BLOG_PAGE + "/*"})
@MultipartConfig(
                fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
                maxFileSize         = 1024 * 1024 * 10, // 10 MB
                maxRequestSize      = 1024 * 1024 * 15  // 15 MB
)
public class CreateBlogController extends HttpServlet {
    private BlogService blogService = ServiceConfiguration.getBlogService();
    private CategoryService categoryService = ServiceConfiguration.getCategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
        if(user == null)
        {
            resp.sendRedirect(User.LOGIN_PAGE+ "?" + User.ACTION + "=" + User.ACTION_NOT_LOGIN);
        }
        else
        {
            BlogDto blogDto = new BlogDto();
            String url = req.getRequestURI();
            //Kiểm tra nếu là chỉnh sửa truyện
            if(url.startsWith(User.UPDATE_BLOG_PAGE))
            {
                String blogIdStr = url.split("/")[2];
                blogDto.setBlogId(Long.parseLong(blogIdStr));
                blogDto = blogService.getOne(blogDto);
                //Xoá các thẻ <p> ở mỗi dòng
                blogService.removePTagContent(blogDto);
                if(blogDto == null)
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

            List<CategoryDto> categories = categoryService.findAll(null, null);

            req.setAttribute("blog", blogDto);
            req.setAttribute("categories", categories);
            RequestDispatcher rd = req.getRequestDispatcher(User.CREATE_BLOG_JSP);

            rd.forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
    {
        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
        if(user == null)
        {
            resp.sendRedirect(User.LOGIN_PAGE+ "?" + User.ACTION + "=" + User.ACTION_NOT_LOGIN);
            return;
        }

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
                req.setAttribute("message", "Tạo bài viết thành công, vui lòng đợi quản trị viên xét duyệt!");
                req.setAttribute("status", "notice");
            }
            //Todo: Thực hiện Dispatcher lại trang này và thông báo thành công
        }
        else
        {
            req.setAttribute("status", "alert");
            req.setAttribute("blog", createBlog);
        }

        List<CategoryDto> categories = categoryService.findAll(null, null);
        req.setAttribute("categories", categories);
        RequestDispatcher rd = req.getRequestDispatcher(User.CREATE_BLOG_JSP);

        rd.forward(req, resp);
    }
}
