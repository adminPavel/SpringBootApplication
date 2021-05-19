package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exeption.ValidationException;
import com.example.demo.repository.MapRepositoryUser;
import com.example.demo.repository.UserLoginRepository;
import com.example.demo.repository.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService {

    private final UserLoginRepository userLoginRepository;
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

    @Override
    public UserDto saveUser(UserDto userDto) throws ValidationException {
        validateUserDto(userDto); //валидация входящих данных о юзере
        User user = userConverter.fromUserDtoToUser(userDto); //конвертация из UserDto в User
        User savedUser = userLoginRepository.save(user);// сохранение в БД
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
                    employee.setName(user.getEmail());
                    employee.setName(user.getSurname());
                    return userConverter.fromUserToUserDto(mapRepository.save(employee));
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userConverter.fromUserToUserDto(mapRepository.save(user));
                });
    }

    @Override
    public void deleteUser(Long userId) throws ValidationException {

        userLoginRepository.deleteById(userId);

    }

    @Override
    public UserDto findUserByLogin(String login) {
        User user = userLoginRepository.findUserByLogin(login);
        if (user != null) {
            return userConverter.fromUserToUserDto(user);
        }
        return null;
    }

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> user = mapRepository.findById(id);
        return userConverter.fromUserToUserDto(user);
    }

    @Override
    public List<UserDto> listOfUsers() {
        return userLoginRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAll() {
        return userLoginRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }
}