package com.appsdist.ms.administracion.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userDist")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String isActive;

    private String password;

    private String email;

    private String suscription;

}
