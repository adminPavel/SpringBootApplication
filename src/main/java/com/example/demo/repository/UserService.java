package com.example.demo.repository;

import com.example.demo.dto.UserDto;
import com.example.demo.exeption.ValidationException;
import java.util.List;

public interface UserService {
    UserDto saveUser (UserDto userDto) throws ValidationException;
    void deleteUser (Long userId) throws ValidationException;
    UserDto findUserByLogin(String login) throws ValidationException;
    UserDto findUserById(Long id) throws ValidationException;
    List<UserDto> findAll();
    UserDto update(UserDto userDto, Long id) throws ValidationException;
    List<UserDto> searchByValue(String key) throws ValidationException;
    boolean checkIfValidOldPassword(UserDto userDto, String oldPassword) throws ValidationException;
    UserDto changePassword(Long id, String newPassword) throws ValidationException;
}
