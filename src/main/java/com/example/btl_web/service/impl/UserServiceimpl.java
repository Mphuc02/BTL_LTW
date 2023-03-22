package com.example.btl_web.service.impl;

import com.example.btl_web.dao.UserDao;
import com.example.btl_web.dao.impl.UserDaoImpl;
import com.example.btl_web.model.User;
import com.example.btl_web.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class UserServiceimpl implements UserService {
    private UserDao userDao = UserDaoImpl.getInstance();

    private static UserServiceimpl userServiceimpl;
    public static UserServiceimpl getInstance()
    {
        if(userServiceimpl == null)
            userServiceimpl = new UserServiceimpl();
        return userServiceimpl;
    }
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean login(String userName, String passWord) {
        User user = userDao.getUserByUserName(userName);

        if(user != null)
        {
            String userNameLogin = user.getUserName();
            String passWordLogin = user.getPassWord();

            if(userName.equals(userNameLogin) && passWordLogin.equals(passWord))
                return true;
        }

        return false;
    }

    @Override
    public int signUp(String userName, String passWord, String passWord_2, String email) {
        if(checkUserNameExisted(userName))
            return 1;
        if(passWord.length() < 6)
            return 2;
        if(!passWord.equals(passWord_2))
            return 3;
        if(!checkEmailValid(email))
            return 4;
        return -1;
    }

    @Override
    public boolean saveUser(String userName, String passWord, String email) {
        Date javaDate = new Date();
        java.sql.Date mySQLDate = new java.sql.Date(javaDate.getTime());

        if(userDao.saveUser(userName,passWord, "USER", email, mySQLDate))
            return true;
        return false;
    }

    private boolean checkUserNameExisted(String userName)
    {
        User user = userDao.getUserByUserName(userName);
        if(user == null)
            return false;
        return true;
    }

    private boolean checkEmailValid(String email)
    {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }
}
