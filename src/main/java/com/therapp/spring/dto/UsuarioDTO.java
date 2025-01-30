package com.therapp.spring.dto;

import com.therapp.spring.modelo.Rol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private String nombre;
    private String nombreUsuario;
    private String email;
    private Rol rol;
}
