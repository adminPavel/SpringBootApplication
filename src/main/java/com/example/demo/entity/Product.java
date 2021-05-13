package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "product")
@Data//ломбок аннотация: генерирует геттеры, сеттеры, иквалс, хеш код методы
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    private Long id;

    @Column
    private String product_name;

    @Column
    private String color;

    @Column
    private Integer price;

}
