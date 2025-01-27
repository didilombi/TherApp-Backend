package com.therapp.spring.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class UsuarioPublicacion {

    private String rol;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @ManyToOne
    @MapsId("publicacionId")
    @JoinColumn(name = "publicacionId")
    private Publicacion publicacion;

     // Getters and setters
     public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }


}
