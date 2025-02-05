package com.therapp.spring.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private String fechaPublicacion;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioPublicacion> usuarios;

    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContenidoPublicacion> contenidos;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public List<UsuarioPublicacion> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioPublicacion> usuarios) {
        this.usuarios = usuarios;
    }

    public List<ContenidoPublicacion> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<ContenidoPublicacion> contenidos) {
        this.contenidos = contenidos;
    }
}
