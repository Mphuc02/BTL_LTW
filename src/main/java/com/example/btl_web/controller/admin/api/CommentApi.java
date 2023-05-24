package com.example.btl_web.controller.admin.api;

import com.example.btl_web.configuration.ServiceConfiguration;
import com.example.btl_web.constant.Constant;
import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.dto.CommentDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.UserBlogService;
import com.example.btl_web.utils.HttpUtils;
import com.example.btl_web.utils.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = Constant.User.USER_COMMENT_API)
public class CommentApi extends HttpServlet {
    private UserBlogService userBlogService = ServiceConfiguration.getUserBlogService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        solveApi(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        solveApi(req, resp);
    }

    private void solveApi(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        CommentDto comment = HttpUtils.of(req.getReader()).toModel(CommentDto.class);
        UserDto userComment = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);
        comment.setUserComment(userComment);

        String[] errors = new String[2];
        boolean validComment = userBlogService.validComment(comment, errors);

        ObjectMapper mapper = new ObjectMapper();
        if(validComment)
        {
            String message = "";
            String method = req.getMethod();
            boolean editCommentStatus = false;

            if(method.equals(Request.POST_METHOD))
            {
                message = "Đăng comment thành công";
                editCommentStatus = userBlogService.saveComment(comment);
            }
            else if(method.equals(Request.PUT_METHOD))
            {
                message = "Xóa comment thành công";
                editCommentStatus = userBlogService.deleteComment(comment);
            }

            if(editCommentStatus)
                mapper.writeValue(resp.getOutputStream(), message);
            else
            {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                mapper.writeValue(resp.getOutputStream(), message);
            }
        }
        else
        {
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            mapper.writeValue(resp.getOutputStream(), errors);
        }
    }
}
