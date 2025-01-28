package com.therapp.spring.modelo;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Paciente extends Usuario {

   private String apellidos;
   private String estado;
   private String sintomas;

    @ManyToMany(mappedBy = "pacientes")
    private Set<Terapeuta> terapeutas;
    
     // Getters and setters

        public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

     public String getEstado() {
      return estado;
  }

  public void setEstado(String estado) {
      this.estado = estado;
  }

  public String getSintomas() {
      return sintomas;
  }

  public void setSintomas(String sintomas) {
      this.sintomas = sintomas;
  }

  public Set<Terapeuta> getTerapeutas() {
      return terapeutas;
  }

  public void setTerapeutas(Set<Terapeuta> terapeutas) {
      this.terapeutas = terapeutas;
  }

}
