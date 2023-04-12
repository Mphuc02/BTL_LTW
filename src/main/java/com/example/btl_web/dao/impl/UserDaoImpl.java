package com.example.btl_web.dao.impl;

import com.example.btl_web.dao.UserDao;
import com.example.btl_web.mapper.impl.UserMapperImpl;
import com.example.btl_web.model.User;

import java.util.List;

public class UserDaoImpl extends GeneralDaoImpl<User> implements UserDao {
    private static UserDaoImpl userDao;
    public static UserDaoImpl getInstance()
    {
        if(userDao == null)
            userDao = new UserDaoImpl();
        return userDao;
    }
    @Override
    public List<User> findAll() {
        String sql = "Select * from users";
        return selectSql(sql, new UserMapperImpl());
    }

    @Override
    public User getUserById(Long id) {
        String sql = "Select * from users where id = ?";
        List<User> users = selectSql(sql, new UserMapperImpl(), id);

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User getUserByUserName(String userName) {
        String sql = "Select * from users where username = ?";

        List<User> results = selectSql(sql, new UserMapperImpl(), userName);

        if(results.size() > 0)
            return results.get(0);
        else
            return null;
    }

    @Override
    public boolean checkUserNameExist() {
        return false;
    }

    @Override
    public boolean saveUser(Object... parameters) {
        String sql = "Insert into users (username, password, role, email, registered_at, status) values(?, ?, ?, ?, ?, 1 )";

        boolean status = updateSql(sql, parameters);
        if(status)
            return true;
        return false;
    }
}
