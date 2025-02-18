package com.therapp.spring.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import com.therapp.spring.modelo.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class PerfilDTO {
    public PerfilDTO(Optional<Usuario> usuario) {
        id = usuario.get().getId();
        fotoPerfil = usuario.get().getFotoPerfil();
        username = usuario.get().getUsername();
        nombre = usuario.get().getNombre();
        email = usuario.get().getEmail();
        telefono = usuario.get().getTelefono();
        fechaNacimiento = usuario.get().getFechaNacimiento();
        biografia = usuario.get().getBiografia();
        ubicacion = usuario.get().getUbicacion();
    }
    
    private Long id;
    private String fotoPerfil;
    private String username;
    private String nombre;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String biografia;
    private String ubicacion;
}
