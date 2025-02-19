package com.therapp.spring.dto;

import com.therapp.spring.modelo.Terapeuta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class TerapeutaMostrarDTO {
    private String nombre;
    private String apellidos;
    private String foto;
    private String experiencia;
    private String especialidad;
    private int precio;
    private boolean premium;
    private String email;

    public TerapeutaMostrarDTO(Terapeuta t){
        this.nombre = t.getUsuario().getNombre();
        this.apellidos = t.getApellidos();
        this.foto = t.getUsuario().getFotoPerfil();
        this.experiencia = t.getExperiencia();
        this.especialidad = t.getEspecialidad();
        this.precio = t.getPrecio();
        this.premium = t.isPremium();
        this.email = t.getUsuario().getEmail();
    }
}
