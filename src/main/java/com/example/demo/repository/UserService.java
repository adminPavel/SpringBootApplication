package com.example.demo.repository;

import com.example.demo.dto.UserDto;
import com.example.demo.exeption.ValidationException;
import java.util.List;

public interface UserService {
    UserDto saveUser (UserDto userDto) throws ValidationException;
    void deleteUser (Long userId) throws ValidationException;
    UserDto findUserByLogin (String login);
    UserDto findUserById(Long id) throws ValidationException;
    List<UserDto> findAll();
    UserDto update(UserDto userDto, Long id) throws ValidationException;
    List<UserDto> listOfUsers();
}
