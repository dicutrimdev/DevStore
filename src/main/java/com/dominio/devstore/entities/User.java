package com.dominio.devstore.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer birth_date;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}
