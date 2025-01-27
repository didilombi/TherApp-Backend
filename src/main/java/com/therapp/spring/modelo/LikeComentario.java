package com.therapp.spring.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LikeComentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "comentarioId")
    private ComentarioPublicacion comentario;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

     // Getters and setters
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ComentarioPublicacion getComentario() {
        return comentario;
    }

    public void setComentario(ComentarioPublicacion comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
