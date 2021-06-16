package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<UserDto> fromUserListToUserDtoList(List<User> users) {
        List<UserDto> usersDTO = new ArrayList<>();
        for (User u: users) {
            UserDto userDto = fromUserToUserDto(u);
            usersDTO.add(userDto);
        }
        return usersDTO;
    }

    public List<User> fromUserDtoListToUserList(List<UserDto> usersDto) {
        List<User> users = new ArrayList<>();
        for (UserDto u: usersDto) {
            User user = fromUserDtoToUser(u);
            users.add(user);
        }
        return users;
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
