package com.example.btl_web.mapper.impl;

import com.example.btl_web.dao.UserDao;
import com.example.btl_web.dao.impl.UserDaoImpl;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.mapper.RowMapper;
import com.example.btl_web.model.Blog;
import com.example.btl_web.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogMapperImpl implements RowMapper {
    private UserDao userDao = UserDaoImpl.getInstance();
    @Override
    public Object mapper(ResultSet resultSet) {
        Blog blog = new Blog();

        try {
            blog.setBlogId(resultSet.getLong("blog_id"));
            blog.setTitle(resultSet.getString("title"));
            blog.setContent(resultSet.getString("content"));
            blog.setImageTitle(resultSet.getString("image_title"));
            blog.setCreateAt(resultSet.getLong("created_at"));

            User user = userDao.getUserById(resultSet.getLong("user_id"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blog;
    }
}
