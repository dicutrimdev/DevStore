package com.dominio.devstore.entities;

import lombok.*;
import jakarta.persistence.*;
import com.dominio.devstore.entities.enums.UserRole;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "TB_USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}
