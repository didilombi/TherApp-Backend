package com.therapp.spring.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Organizaciones extends Usuario {

    private String cif;
    private String direccion;
    private String telefono;
    private String email;
    private String descripcion;
    private String sitioweb;

    @OneToMany(mappedBy = "organizacion")
    private List<Terapeuta> terapeutas;

    // Getters and setters

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSitioweb() {
        return sitioweb;
    }

    public void setSitioweb(String sitioweb) {
        this.sitioweb = sitioweb;
    }

    public List<Terapeuta> getTerapeutas() {
        return terapeutas;
    }

    public void setTerapeutas(List<Terapeuta> terapeutas) {
        this.terapeutas = terapeutas;
    }
}
