package com.example.btl_web.service.impl;

import com.example.btl_web.dao.UserDao;
import com.example.btl_web.dao.impl.UserDaoImpl;
import com.example.btl_web.dto.UserDto;
import com.example.btl_web.model.User;
import com.example.btl_web.paging.Pageable;
import com.example.btl_web.service.UserService;
import com.example.btl_web.utils.ConvertUtils;

import java.lang.reflect.Field;
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
    public List<UserDto> findAll(Pageable pageable, UserDto dto) {
        StringBuilder sql = new StringBuilder("SELECT * FROM USERS WHERE ( 1 = 1)");
        sql.append(addAndClause(pageable, dto));

        List<User> users = userDao.findAll(sql.toString());
        List<UserDto> dtos = new ArrayList<>();

        for(User user: users)
        {
            dtos.add(ConvertUtils.convertEntityToDto(user, UserDto.class));
        }
        return dtos;
    }


    @Override
    public UserDto login(String userName, String passWord) {
        UserDto userDto = new UserDto();
        userDto.setUserName(userName);
        userDto.setPassWord(passWord);

        StringBuilder sql = new StringBuilder("SELECT * FROM USERS WHERE (1 = 1)");
        sql.append(addAndClause(null, userDto));

        List<User> users = userDao.getUserByCondition(sql.toString());

        return users.isEmpty() ? null : ConvertUtils.convertEntityToDto(users.get(0), UserDto.class);

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
    public Long saveUser(UserDto userDto) {
        Date timeStamp = new Date();
        String sql = "INSERT INTO USERS (email, password, registered_at, role, username, status) VALUES (?, ?, ?, ?, ?, 1)";
        return userDao.saveUser(sql,userDto.getEmail(), userDto.getPassWord(), timeStamp.getTime(), "USER", userDto.getUserName());
    }

    @Override
    public Long updateUser(UserDto dto) {
        StringBuilder sql = new StringBuilder("UPDATE USERS SET user_id = " + dto.getUserId()) ;
        sql.append(addUpdateClause(dto));
        return userDao.saveUser(sql.toString());
    }

    private boolean checkUserNameExisted(String userName)
    {
        String sql = "SELECT * FROM USERS WHERE username = ?";
        List<User> users = userDao.getUserByCondition(sql.toString(), userName);

        User user = users.isEmpty() ? null: users.get(0);

        if(user == null)
            return false;
        return true;
    }
    @Override
    public long countUsers() {
        String sql = "SELECT COUNT(user_id) FROM USERS";
        return userDao.countItems(sql);
    }
    private StringBuilder addAndClause(Pageable pageable,UserDto userDto)
    {
        StringBuilder sb = new StringBuilder();
        if(userDto != null)
        {
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
                sb.append(" AND user_id = " + userId );
            if(status != null)
                sb.append(" AND status = " + status);
            if(userName != null)
                sb.append(" AND username = '" + userName + "'");
            if(passWord != null)
                sb.append(" AND password = '" + passWord + "'");
            if(email != null)
                sb.append(" AND email = '" + email + "'");
            if(role != null)
                sb.append(" AND role = '" + role + "'");
            if(address != null)
                sb.append(" AND address = '" + address + "'");
            if(phone != null)
                sb.append(" AND phone = '" + phone +  "'");
            if(fullName != null)
                sb.append(" AND full_name = '" + fullName + "'");
            if(registeredAt != null)
                sb.append(" AND registered_at = " + registeredAt);
        }

        if(pageable != null)
            sb.append(pageable.addPagingation());

        return sb;
    }
    private StringBuilder addUpdateClause(UserDto dto)
    {
        StringBuilder sb = new StringBuilder();
        String userName = dto.getUserName();
        String passWord = dto.getPassWord();
        String email = dto.getEmail();
        String role = dto.getRole();
        String address = dto.getAddress();
        String phone = dto.getPhone();
        String fullName = dto.getFullName();
        Long timeStamp = null;

        if(dto.getRegisteredAt() != null)
            timeStamp = ConvertUtils.convertStringDateToLong(dto.getRegisteredAt());
        Integer status = dto.getStatus();

        if(userName != null)
            sb.append(", username = '" + userName + "'");
        if(passWord != null)
            sb.append(", password = '" + passWord + "'");
        if(email != null)
            sb.append(", email = '" + email + "'");
        if(role != null)
            sb.append(", role = '" + role + "'");
        if(address != null)
            sb.append(", address = '" + address + "'");
        if(phone != null)
            sb.append(", phone = '" + phone + "'");
        if(fullName != null)
            sb.append(", full_name = '" + fullName + "'");
        if(timeStamp != null)
            sb.append(", registered_at = " + timeStamp);
        if(status != null)
            sb.append(", status = " + status);
        sb.append(" WHERE user_id = " + dto.getUserId());
        return sb;
    }
    private boolean checkEmailValid(String email)
    {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }
}
