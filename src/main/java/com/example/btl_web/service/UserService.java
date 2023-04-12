package com.example.btl_web.service;

import com.example.btl_web.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto login(String userName, String passWord);
    int signUp(String userName, String passWord, String passWord_2, String email);
    boolean saveUser(String userName, String passWord, String email);
    boolean updateUser(UserDto dto);
}