package com.therapp.spring.modelo;

import java.util.Set;
import jakarta.persistence.Entity;
<<<<<<< HEAD
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
=======
import jakarta.persistence.Table;
>>>>>>> a77ffc3 (implementacion de los DTO de terapeuta y usuario)
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
<<<<<<< HEAD
public class Terapeuta {
=======
@Table(name = "terapeutas")
public class Terapeuta extends Usuario{
>>>>>>> a77ffc3 (implementacion de los DTO de terapeuta y usuario)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nColegiado;
    private String apellidos;
    private String experiencia;
    private String especialidad;
    private String idiomas;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

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
        inverseJoinColumns = @JoinColumn(name = "especialidadId")
    )
    private Set<Especialidad> especialidades;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
