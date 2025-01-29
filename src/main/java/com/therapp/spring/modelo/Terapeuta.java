package com.therapp.spring.modelo;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Terapeuta extends Usuario{

    private String nColegiado;
    private String apellidos;
    private String experiencia;
    private String especialidad;
    private String idiomas;

    public Terapeuta() {} //constructor vacio

    // Constructor con parámetros
    public Terapeuta(String nombre, String nombreUsuario, String email, String clave, String fotoPerfil, Rol rol, String dni, LocalDate fechaNacimiento, String telefono, String ubicacion, String biografia, String nColegiado, String apellidos, String experiencia, String especialidad, String idiomas) {
        super(nombre, nombreUsuario, email, clave, fotoPerfil, rol, dni, fechaNacimiento, telefono, ubicacion, biografia); 
        this.nColegiado = nColegiado;
        this.apellidos = apellidos;
        this.experiencia = experiencia;
        this.especialidad = especialidad;
        this.idiomas = idiomas;
    }

    public Terapeuta(Usuario user, String nColegiado, String apellidos, String experiencia, String especialidad, String idiomas) {
        super(user.getNombre(), user.getNombreUsuario(), user.getEmail(), user.getClave(), user.getFotoPerfil(), user.getRol(), user.getDni(), user.getFechaNacimiento(), user.getTelefono(), user.getUbicacion(), user.getBiografia());
        this.nColegiado = nColegiado;
        this.apellidos = apellidos;
        this.experiencia = experiencia;
        this.especialidad = especialidad;
        this.idiomas = idiomas;
        super.setId(user.getId());
    }


    @ManyToOne
    @JoinColumn(name = "organizacion_id")
    private Organizaciones organizacion;

    @ManyToMany
    @JoinTable(
        name = "terapeuta_paciente",
        joinColumns = @JoinColumn(name = "terapeutaId"),
        inverseJoinColumns = @JoinColumn(name = "pacienteId")
    )
    private Set<Paciente> pacientes;

    @ManyToMany
    @JoinTable(
        name = "terapeuta_especialidad",
        joinColumns = @JoinColumn(name = "terapeutaId"),
        inverseJoinColumns = @JoinColumn(name = "pacienteId")
    )
    private Set<Especialidad> especialidades;

    // Getters and setters
    public String getnColegiado() {
        return nColegiado;
    }

    public void setnColegiado(String nColegiado) {
        this.nColegiado = nColegiado;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Organizaciones getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizaciones organizacion) {
        this.organizacion = organizacion;
    }

    public Set<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public Set<Especialidad> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

}
