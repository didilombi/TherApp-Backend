package com.therapp.spring.modelo;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Paciente extends Usuario {

   private String apellidos;
   private String estado;
   private String sintomas;

    @ManyToMany(mappedBy = "pacientes")
    private Set<Terapeuta> terapeutas;
}
