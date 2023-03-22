package com.example.btl_web.dao;

import com.example.btl_web.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User getUserByUserName(String userName);
    boolean checkUserNameExist();
    boolean saveUser(Object... parameters);
}
