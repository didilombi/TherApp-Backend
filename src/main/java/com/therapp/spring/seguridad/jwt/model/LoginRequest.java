package com.therapp.spring.seguridad.jwt.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "El identificador no debe estar vacío")
    private String identifier;

    @NotBlank(message = "La clave no debe estar vacía")
    private String password;
}
