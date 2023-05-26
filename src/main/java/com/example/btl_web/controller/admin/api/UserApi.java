package com.example.btl_web.controller.admin.api;

import com.example.btl_web.configuration.ServiceConfiguration;
import com.example.btl_web.constant.Constant;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.service.UserService;
import com.example.btl_web.utils.HttpUtils;
import com.example.btl_web.utils.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import com.example.btl_web.constant.Constant.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(urlPatterns = Admin.USER_API)
public class UserApi extends HttpServlet {
    private UserService userService = ServiceConfiguration.getUserService();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        solveApi(req, resp);
    }
    private void solveApi(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        UserDto user = (UserDto) SessionUtils.getInstance().getValue(req, Constant.USER_MODEL);

        //Tìm kiếm thông tin người dùng được chỉnh sửa
        UserDto receiveApi = HttpUtils.of(req.getReader()).toModel(UserDto.class);
        UserDto editUser = userService.findOneById(receiveApi.getUserId());
        editUser.setStatus(receiveApi.getStatus());

        String errors[] = new String[4];
        //Kiểm tra xem User đang đăng nhập có quyền chỉnh sửa thông tin này không
        boolean validStatus = userService.validUpdate(user, errors);
        if(user.getRole() < editUser.getRole())
        {
            validStatus = false;
            errors[0] = "Bạn không có quyền này";
        }
        ObjectMapper mapper = new ObjectMapper();

        if(validStatus)
        {
            Long status = userService.updateUser(editUser);
            if(status != null)
            {
                errors[0] = "Cập nhật thành công!";
                mapper.writeValue(resp.getOutputStream(), errors);
                return;
            }
        }

        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        mapper.writeValue(resp.getOutputStream(), errors);
    }
}
