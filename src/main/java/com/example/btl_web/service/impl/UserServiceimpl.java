package com.example.btl_web.service.impl;

import com.example.btl_web.dao.UserDao;
import com.example.btl_web.dao.impl.UserDaoImpl;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.model.User;
import com.example.btl_web.service.UserService;
import com.example.btl_web.utils.ConvertUtils;

import java.util.ArrayList;
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
    public List<UserDto> findAll() {
        List<User> users = userDao.findAll();
        List<UserDto> dtos = new ArrayList<>();

        for(User user: users)
        {
            dtos.add(ConvertUtils.convertEntityToDto(user, UserDto.class));
        }
        return dtos;
    }

    @Override
    public UserDto login(String userName, String passWord) {
        User user = userDao.getUserByUserName(userName);

        if(user != null)
        {
            String userNameLogin = user.getUserName();
            String passWordLogin = user.getPassWord();

            if(userName.equals(userNameLogin) && passWordLogin.equals(passWord))
            {
                return ConvertUtils.convertEntityToDto(user, UserDto.class);
            }
        }
        return null;
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
        long timeStamp = javaDate.getTime();
        if(userDao.saveUser(userName,passWord, "USER", email, timeStamp))
            return true;
        return false;
    }

    @Override
    public boolean updateUser(UserDto dto) {
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

    private StringBuilder addAndClause(UserDto userDto)
    {
        StringBuilder sb = new StringBuilder();
        Long userId = userDto.getUserId();
        Integer status = userDto.getStatus();
        String userName = userDto.getUserName();
        String passWord = userDto.getPassWord();
        String email = userDto.getEmail();
        String role = userDto.getRole();
        String address = userDto.getAddress();
        String phone = userDto.getPhone();
        String fullName = userDto.getPhone();
        String registeredAt = userDto.getRegisteredAt();

        if(userId != null)
            sb.append(" AND user_id = ?");
        if(status != null)
            sb.append(" AND status = ?");
        if(userName != null)
            sb.append(" AND username = ?");

        return sb;
    }
}
