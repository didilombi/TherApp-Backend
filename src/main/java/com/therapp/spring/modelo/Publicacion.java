package com.therapp.spring.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String texto;
    private String fechaPublicacion;

    @OneToMany(mappedBy = "publicacion")
    private List<UsuarioPublicacion> usuarios;

    @OneToMany(mappedBy = "publicacion")
    private List<ContenidoPublicacion> contenidos;

    
}
