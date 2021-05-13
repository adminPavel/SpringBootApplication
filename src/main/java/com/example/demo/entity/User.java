package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "users_table")
@Data//ломбок аннотация: генерирует геттеры, сеттеры, иквалс, хеш код методы
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;


    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column (name = "email")
    private String email;

}
