package com.personalproject.blogapi.services;

import com.personalproject.blogapi.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto userDto);
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
