package com.example.btl_web.mapper.impl;

import com.example.btl_web.mapper.RowMapper;
import com.example.btl_web.model.Blog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogMapperImpl implements RowMapper {
    @Override
    public Object mapper(ResultSet resultSet) {
        Blog blog = new Blog();

        try {
            blog.setBlogId(resultSet.getLong("blog_id"));
            blog.setTitle(resultSet.getString("title"));
            blog.setContent(resultSet.getString("content"));
            blog.setImageTitle(resultSet.getString("image_title"));
            blog.setCreateAt(resultSet.getDate("created_at"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blog;
    }
}
