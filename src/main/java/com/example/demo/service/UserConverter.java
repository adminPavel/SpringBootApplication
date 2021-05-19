package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserConverter {

    public UserDto fromUserToUserDto(Optional<User> user) {
        return UserDto.builder()
                .id(user.orElseThrow().getId())
                .name(user.orElseThrow().getName())
                .surname(user.orElseThrow().getSurname())
                .login(user.orElseThrow().getLogin())
                .password(user.orElseThrow().getPassword())
                .email(user.orElseThrow().getEmail())
                .build();
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .login(user.getLogin())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public User fromUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
    }
}
