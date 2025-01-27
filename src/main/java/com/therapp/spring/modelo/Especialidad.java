package com.therapp.spring.modelo;

import java.util.Set;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;

    @ManyToMany(mappedBy = "Terapeuta_especialidad")
    private Set<Terapeuta> terapeutas;
}
