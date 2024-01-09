package com.appsdist.ms.administracion.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {

    private String firstName;

    private String lastName;

    private String isActive;

    private String password;

    private String email;

    private String suscription;
}
