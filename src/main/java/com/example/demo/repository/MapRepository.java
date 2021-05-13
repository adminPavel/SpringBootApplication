package com.example.demo.repository;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MapRepository extends JpaRepository<User, Long> {
}
