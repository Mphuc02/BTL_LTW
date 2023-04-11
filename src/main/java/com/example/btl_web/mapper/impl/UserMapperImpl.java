package com.example.btl_web.mapper.impl;

import com.example.btl_web.mapper.RowMapper;
import com.example.btl_web.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapperImpl implements RowMapper<User> {

    @Override
    public User mapper(ResultSet resultSet) {
        User user = new User();

        try {
            user.setUserId(resultSet.getLong("user_id"));
            user.setAddress(resultSet.getString("address"));
            user.setEmail(resultSet.getString("email"));
            user.setFullName(resultSet.getString("full_name"));
            user.setPassWord(resultSet.getString("password"));
            user.setPhone(resultSet.getString("phone"));
            user.setRegisteredAt(resultSet.getLong("registered_at"));
            user.setRole(resultSet.getString("role"));
            user.setUserName(resultSet.getString("username"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
