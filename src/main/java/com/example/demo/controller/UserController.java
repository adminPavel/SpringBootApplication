package com.example.demo.controller;

import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/users")
@Log
@AllArgsConstructor
@Data
public class UserController {

    private final UserService userService;
    private final MapRepositoryUser mapRepository;
    private final UserConverter userConverter;

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
    public UserDto findUserByLogin(@RequestParam String login) throws ValidationException {
        log.info("Handling find by login request: " + login);
        return userService.findUserByLogin(login);
    }

    @GetMapping("/findUserById")
    public UserDto findById(@RequestParam Long id) throws ValidationException {
        log.info("Handling find by id request: " + id);
        return userService.findUserById(id);
    }


    @PostMapping("/updatePassword")
    //@PreAuthorize("hasRole('READ_PRIVILEGE')")
    public UserDto changeUserPassword(@RequestParam("id") Long id,
                                      //@RequestParam("oldPassword") String oldPassword,
                                      @RequestParam("newPassword") String newPassword
                                      ) throws ValidationException {
        log.info("Handling update user password: ");
        return userService.changePassword(id, newPassword);
    }

    /*
    for search in Postman need to enter :
    localhost:8080/users/search/1@gmail.com
    KEY : key
    VALUE: 1@gmail.com (for exemple)
     */
    @GetMapping("/search/{key}")
    public List<UserDto> searchByValue(@RequestParam(value = "key") @PathVariable String key) throws ValidationException {
        log.info("Handling find by key word request: " + key);
        return userService.searchByValue(key);
    }

    /*
    Update all information about user (email, login, name, surname, password)
     */
    @PutMapping("/update/{id}")
    public UserDto update(@RequestBody UserDto userDto, @PathVariable Long id) throws ValidationException {
        log.info("Handling update by id request: " + id);
        return userService.update(userDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) throws ValidationException {
        log.info("Handling delete user request: " + id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
