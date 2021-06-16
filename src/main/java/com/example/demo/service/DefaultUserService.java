package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exeption.ValidationException;
import com.example.demo.repository.MapRepositoryUser;
import com.example.demo.repository.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
@Data
public class DefaultUserService implements UserService {

  //  private final UserService userService;
    private final UserConverter userConverter;
    private final MapRepositoryUser mapRepository;

    private void validateUserDto(UserDto usersDto) throws ValidationException {
        if (isNull(usersDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(usersDto.getLogin()) || usersDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }

//    @Override
//    public UserDto saveUserAlex(User user) throws ValidationException {
//        User alex = User.of("Alex", "123", "123@gmail.com");
//        alex.setName("Alex");
//        alex.setSurname("Ivanov");
//
//        validateUserDto(userDto); //валидация входящих данных о юзере
//        User user = userConverter.fromUserDtoToUser(userDto); //конвертация из UserDto в User
//        User savedUser = mapRepository.save(user);// сохранение в БД
//        return userConverter.fromUserToUserDto(savedUser); //конвертация сохраненного юзера обратно в UserDTo и возврат
//    }

    @Override
    public UserDto saveUser(UserDto userDto) throws ValidationException {
        validateUserDto(userDto); //валидация входящих данных о юзере
        User user = userConverter.fromUserDtoToUser(userDto); //конвертация из UserDto в User
        User savedUser = mapRepository.save(user);// сохранение в БД
        return userConverter.fromUserToUserDto(savedUser); //конвертация сохраненного юзера обратно в UserDTo и возврат
    }

    @Override
    public UserDto update(UserDto userDto, Long id) throws ValidationException {
        validateUserDto(userDto);
        User user = userConverter.fromUserDtoToUser(userDto);

        return mapRepository.findById(id)
                .map(employee -> {
                    employee.setName(user.getName());
                    employee.setLogin(user.getLogin());
                    employee.setPassword(user.getPassword());
                    employee.setEmail(user.getEmail());
                    employee.setSurname(user.getSurname());
                    return userConverter.fromUserToUserDto(mapRepository.save(employee));
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userConverter.fromUserToUserDto(mapRepository.save(user));
                });
    }

    @Override
    public void deleteUser(Long userId) {
        mapRepository.deleteById(userId);
    }

    @Override
    public UserDto findUserByLogin(String login) {
        User user = mapRepository.findUserByLogin(login);
        return userConverter.fromUserToUserDto(user);
    }

    @Override
    public List<UserDto> searchByValue(String key) {
        if(key != null) {
            return userConverter.fromUserListToUserDtoList(mapRepository.searchByValue(key));
        }
        return mapRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkIfValidOldPassword (UserDto userDto, String newPassword) {
        if(userDto.getPassword().equals(newPassword)) {
            return true;
        }
        return false;
    }

    @Override
    public UserDto changePassword(Long id, String newPassword) throws ValidationException{
        UserDto userDto = findUserById(id);
        //UserDto userDto = userConverter.fromUserToUserDto(user);
        System.out.println("000000000000000000");
        if (checkIfValidOldPassword(userDto, newPassword)) {
            ValidationException validationException = new ValidationException("New password  equals OldPassword");
            System.out.println(validationException);
        }
        System.out.println("1111111111111111111");
        userDto.setPassword(newPassword);
        System.out.println("2222222222222222222");

        saveUser(userDto);
        System.out.println("3333333333333333");

        return userDto;
    }

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> user = mapRepository.findById(id);
        return userConverter.fromUserToUserDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return mapRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }
}