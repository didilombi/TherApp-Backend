package com.therapp.spring.dto;

import com.therapp.spring.modelo.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UsuarioDTO {
    private String nombre;
    private String clave;
    private String nombreUsuario;
    private String email;
    private Rol rol;
    private String especialidad; //solo para terapeutas
    private String cif; //solo para organizaciones
}
