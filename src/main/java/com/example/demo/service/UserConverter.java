package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class UserConverter {

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

    public UserDto fromUserToUserDtoOptional(Optional<User> user) {

        UserDto userDto = new UserDto();

        if (userDto.getId() == null) {
            throw new NoSuchElementException("No login4 value");
        }
        userDto.setId(user.orElseThrow().getId());

        userDto.setName(user.orElseThrow().getName());
        userDto.setSurname(user.orElseThrow().getSurname());
        userDto.setLogin(user.orElseThrow().getLogin());
        userDto.setPassword(user.orElseThrow().getPassword());
        userDto.setEmail(user.orElseThrow().getEmail());
        return userDto;
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

    /*
    public UserDto fromUserToUserDtoOptional(Optional<User> user) {
        return UserDto.builder()
                .id(user.get().getId())
                .name(user.get().getName())
                .surname(user.get().getSurname())
                .login(user.get().getLogin())
                .password(user.get().getPassword())
                .email(user.get().getEmail())
                .build();
    }
     */
}
