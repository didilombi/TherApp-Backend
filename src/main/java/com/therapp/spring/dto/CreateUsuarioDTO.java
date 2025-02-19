package com.therapp.spring.dto;

import java.time.LocalDate;
import java.util.Set;
import com.therapp.spring.modelo.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUsuarioDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String clave;

    private Set<Rol> rol;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotBlank
    private String telefono;

    private String ubicacion;
}
