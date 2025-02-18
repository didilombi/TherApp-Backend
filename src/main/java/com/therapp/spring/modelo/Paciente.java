package com.therapp.spring.modelo;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Paciente extends Usuario {

   private String apellidos;
   private String estado;
   private String sintomas;

    @ManyToMany(mappedBy = "pacientes")
    private Set<Terapeuta> terapeutas;
}
