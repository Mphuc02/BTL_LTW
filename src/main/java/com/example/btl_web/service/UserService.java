package com.example.btl_web.service;

import com.example.btl_web.dto.UserDto;
import com.example.btl_web.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    UserDto login(String userName, String passWord);
    int signUp(String userName, String passWord, String passWord_2, String email);
    boolean saveUser(String userName, String passWord, String email);

}
