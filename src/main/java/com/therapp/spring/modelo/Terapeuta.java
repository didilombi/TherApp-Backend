package com.therapp.spring.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "terapeutas")
public class Terapeuta extends Usuario {

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
    private Organizacion organizacion;

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

    @OneToMany(mappedBy = "terapeuta")
    @JsonBackReference // Esta anotación evita la recursión infinita en la lista de mensajes
    private List<Mensaje> mensajes;
    // Ignorar la propiedad 'usuario' para evitar la recursión infinita
    @JsonIgnore
    public Usuario getUsuario() {
        return usuario;
    }

    public Terapeuta() {}

    public Terapeuta(String nombre, String nombreUsuario,String email, String clave, String fotoPerfil, Rol rol,String dni,LocalDate fechaNacimiento,String telefono, String ubicacion, String biografia, String nColegiado, String apellidos, String experiencia, String especialidad, String idiomas) {
        super(nombre, nombreUsuario, email, clave, fotoPerfil, rol, dni, fechaNacimiento, telefono, ubicacion, biografia);
        this.nColegiado = nColegiado;
        this.apellidos = apellidos;
        this.experiencia = experiencia;
        this.especialidad = especialidad;
        this.idiomas = idiomas;
    }
}
