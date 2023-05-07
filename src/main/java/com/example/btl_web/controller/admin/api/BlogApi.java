package com.example.btl_web.controller.admin.api;

import com.example.btl_web.configuration.ServiceConfiguration;
import com.example.btl_web.constant.Constant;
import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.BlogService;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.utils.HttpUtils;
import com.example.btl_web.utils.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = Admin.BLOGS_API)
public class BlogApi extends HttpServlet {
    private BlogService blogService = ServiceConfiguration.getBlogService();
    private CategoryService categoryService = ServiceConfiguration.getCategoryService();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        UserDto userEdit = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
        BlogDto editBlot = HttpUtils.of(req.getReader()).toModel(BlogDto.class);

        boolean validBlog = blogService.validUpdateBlog(req, editBlot, userEdit.getUserId());

        ObjectMapper mapper = null;
        if (validBlog) {
            mapper = new ObjectMapper();

            Long editStatus = blogService.update(editBlot);
            if (editStatus != null)
                mapper.writeValue(resp.getOutputStream(), "Cập nhật thành công!");
            else {
                resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                mapper.writeValue(resp.getOutputStream(), "Cập nhật không thành công!");
            }
        } else {
            //Sửa lại message sao cho chuẩn
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("message", req.getAttribute("message"))));
        }
    }
}