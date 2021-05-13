package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserLoginRepository extends JpaRepository<User, Long> {
    User findUserByLogin (String login);
}
