package com.website.blog.services;

import java.util.List;

import com.website.blog.payloads.userDto;

public interface userService {
    userDto createUser(userDto user);

    userDto updateUser(userDto user, Integer userId);

    userDto getUserById(Integer userId);

    List <userDto> getAllUsers();

    void deleteUser(Integer userId);
}

