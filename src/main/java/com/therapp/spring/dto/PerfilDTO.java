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
    public PerfilDTO(Optional<Usuario> byId) {
        id = byId.get().getId();
        fotoPerfil = byId.get().getFotoPerfil();
        username = byId.get().getUsername();
        nombre = byId.get().getNombre();
        email = byId.get().getEmail();
        telefono = byId.get().getTelefono();
        fechaNacimiento = byId.get().getFechaNacimiento();
        biografia = byId.get().getBiografia();
        ubicacion = byId.get().getUbicacion();
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
