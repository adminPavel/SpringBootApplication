package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import lombok.Builder;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "users_table")
@Data//ломбок аннотация: генерирует геттеры, сеттеры, иквалс, хеш код методы
@AllArgsConstructor
@RequiredArgsConstructor(staticName="of")
@NoArgsConstructor
@Builder
public class User {

    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long id;

    @OneToMany(mappedBy = "id",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @Nullable
    @Column(name = "name")
    private String name;

    @Nullable
    @Column(name = "surname")
    private String surname;

    @Column(name = "login")
    @NonNull
    private String login;

    @Column(name = "password")
    @NonNull
    private String password;

    @Email
    @Column (name = "email")
    @NonNull
    private String email;

}
