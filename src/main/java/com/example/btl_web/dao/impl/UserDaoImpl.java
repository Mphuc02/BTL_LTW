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
    public List<User> findAll(String sql, Object... parameters) {
        return selectSql(sql, new UserMapperImpl(), parameters);
    }

    @Override
    public List<User> getUserByCondition(String sql, Object... parameters) {
        List<User> users = selectSql(sql, new UserMapperImpl(), parameters);

        return users;
    }

    @Override
    public long countItems(String sql, Object... parameters) {
        return countSql(sql, parameters);
    }

    @Override
    public boolean checkUserNameExist(String sql, Object... parameters) {
        return false;
    }

    @Override
    public boolean saveUser(String sql, Object... parameters) {
        boolean status = updateSql(sql, parameters);
        if(status)
            return true;
        return false;
    }
}
