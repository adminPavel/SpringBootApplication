package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exeption.ValidationException;
import com.example.demo.repository.MapRepositoryUser;
import com.example.demo.repository.UserService;
import com.example.demo.service.UserConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Log
@AllArgsConstructor
@Data
public class UserController {

    private final UserService userService;
    private final MapRepositoryUser mapRepository;
    private final UserConverter userConverter;

//    @RequestMapping("/")
//    public String row() {
//        System.out.println("hello");
//        log.info("Handling review list users: ");
//        System.out.println("hello");
//        return null;
//    }

    @PostMapping("/save")
    public UserDto saveUsers(@RequestBody UserDto userDto) throws ValidationException {
        log.info("Handling save users: " + userDto);
        return userService.saveUser(userDto);
    }

    @GetMapping("/findAll")
    public List<UserDto> findAllUsers() {
        log.info("Handling find all users request: ");
        return userService.findAll();
    }

    @GetMapping("/findUserByLogin")
    public UserDto findUserByLogin(@RequestParam String login) {
        log.info("Handling find by login request: " + login);
        return userService.findUserByLogin(login);
    }

    @GetMapping("/findUserById")
    public UserDto findById(@RequestParam Long id) throws ValidationException {
        log.info("Handling find by login request: " + id);
        Optional<User> user = mapRepository.findById(id);
        //return userConverter.fromUserToUserDto(user);
        return userService.findUserById(userConverter.fromUserToUserDto(user).getId());
    }

    @PutMapping("/update/{id}")
    public UserDto update(@RequestBody UserDto userDto, @PathVariable Long id) throws ValidationException {
        log.info("Handling find by login request: " + id);
        return userService.update(userDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) throws ValidationException {
        log.info("Handling delete user request: " + id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
