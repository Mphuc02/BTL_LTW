package com.example.btl_web.service;

import com.example.btl_web.dto.UserDto;
import com.example.btl_web.paging.Pageable;

import java.util.List;

public interface UserService{
    List<UserDto> findAll(Pageable pageable, UserDto dto);
    long countUsers();
    UserDto login(String userName, String passWord);
    int signUp(String userName, String passWord, String passWord_2, String email);
    Long saveUser(UserDto userDto);
    Long updateUser(UserDto dto);
}