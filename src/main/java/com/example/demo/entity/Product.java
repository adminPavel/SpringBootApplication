package com.example.demo.entity;

import lombok.*;
import javax.persistence.*;
import lombok.Builder;
import org.springframework.lang.Nullable;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Product product;

    @Column
    @Nullable
    private String productname;

    @Column
    @Nullable
    private String color;

    @Column
    @Nullable
    private Integer price;

}
