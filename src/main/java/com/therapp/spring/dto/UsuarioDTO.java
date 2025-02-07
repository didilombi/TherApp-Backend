package com.therapp.spring.dto;

import com.therapp.spring.modelo.Rol;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private String nombre;
    private String clave;
    private String nombreUsuario;
    private String email;
    private Rol rol;
    private String especialidad; //solo para terapeutas
    private String cif; //solo para organizaciones
}
