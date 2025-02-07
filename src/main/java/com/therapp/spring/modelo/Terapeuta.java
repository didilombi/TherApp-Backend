package com.therapp.spring.modelo;

import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "terapeutas")
public class Terapeuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nColegiado;
    private String apellidos;
    private String experiencia;
    private String especialidad;
    private String idiomas;
    private int precio;
    private boolean premium;
    

    @OneToOne
    @JoinColumn(name = "usuario_id") // Relaciona con Usuario
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

    public Usuario getUsuario() {
        return usuario;
    }
}
