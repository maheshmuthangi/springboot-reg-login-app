package com.auth.registrationlogindemo.service;

import java.util.List;

import com.auth.registrationlogindemo.dto.UserDto;
import com.auth.registrationlogindemo.entity.User;

public interface UserService {
	
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
