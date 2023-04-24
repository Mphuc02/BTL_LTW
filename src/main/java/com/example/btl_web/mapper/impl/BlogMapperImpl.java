package com.example.btl_web.mapper.impl;

import com.example.btl_web.dto.UserDto;
import com.example.btl_web.mapper.RowMapper;
import com.example.btl_web.model.Blog;
import com.example.btl_web.service.CategoryService;
import com.example.btl_web.service.UserService;
import com.example.btl_web.service.impl.CategoryServiceImpl;
import com.example.btl_web.service.impl.UserServiceimpl;
import com.example.btl_web.utils.ConvertUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BlogMapperImpl implements RowMapper {
    private UserService userService = UserServiceimpl.getInstance();
    @Override
    public Object mapper(ResultSet resultSet) {
        Blog blog = new Blog();

        try {
            blog.setBlogId(resultSet.getLong("blog_id"));
            blog.setTitle(resultSet.getString("title"));
            blog.setContent(resultSet.getString("content"));
            blog.setImageTitle(resultSet.getString("image_title"));
            blog.setCreatedAt(resultSet.getLong("created_at"));
            blog.setStatus(resultSet.getInt("status"));

            UserDto userDto = new UserDto();
            userDto.setUserId(resultSet.getLong("user_id"));
            List<UserDto> userDtos = userService.findAll(null, userDto);
            UserDto user = userDtos.isEmpty() ? null: userDtos.get(0);
            blog.setUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blog;
    }
}
