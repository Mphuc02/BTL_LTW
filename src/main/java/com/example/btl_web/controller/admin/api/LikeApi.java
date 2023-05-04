package com.example.btl_web.controller.admin.api;

import com.example.btl_web.configuration.ServiceConfiguration;
import com.example.btl_web.constant.Constant;
import com.example.btl_web.constant.Constant.*;
import com.example.btl_web.dto.BlogDto;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.UserBlogService;
import com.example.btl_web.service.UserService;
import com.example.btl_web.utils.HttpUtils;
import com.example.btl_web.utils.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = User.USER_LIKE_API)
public class LikeApi extends HttpServlet {
    private UserService userService = ServiceConfiguration.getUserService();
    private UserBlogService userBlogService = ServiceConfiguration.getUserBlogService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        solveApi(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        solveApi(req, resp);
    }

    private void solveApi(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        String method = req.getMethod();

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);

        ObjectMapper mapper = new ObjectMapper();
        String[] errors = new String[1];
        String timevalid = userService.checkLastAction(user.getUserId());
        if(timevalid == null)
        {
            BlogDto likedBlog = HttpUtils.of(req.getReader()).toModel(BlogDto.class);

            boolean statusLiked = false;
            if(method.equals(Request.POST_METHOD))
                statusLiked = userBlogService.likeThisBlog(likedBlog.getBlogId(), user.getUserId());
            else if(method.equals(Request.DELETE_METHOD))
                statusLiked = userBlogService.removeLikeThisBlog(likedBlog.getBlogId(), user.getUserId());

            if(statusLiked)
            {
                resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("message", "Bạn đã thích bài viết này!")));
                return;
            }
            else
            {
                resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("errors", "Bạn phải đăng nhập thì mới có thể like bài viết này!")));
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        errors[0] = timevalid;
        resp.getOutputStream().write(mapper.writeValueAsBytes(Collections.singletonMap("errors", errors)));
        resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
    }
}
