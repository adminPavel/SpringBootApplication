package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MapRepositoryUser extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
    @Query("SELECT u FROM User u WHERE CONCAT(u.id,'',u.email,'',u.login,'',u.name,'',u.password,'',u.surname)LIKE %:key%")
    List<User> searchByValue(String key);
}
