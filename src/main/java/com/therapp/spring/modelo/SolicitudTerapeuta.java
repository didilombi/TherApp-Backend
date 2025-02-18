package com.therapp.spring.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solicitudes")
public class SolicitudTerapeuta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String apellidos;
    private String nColegiado;
    private String experiencia;
    private String especialidad;
    private int precio;

    @OneToOne
    @JoinColumn(name = "usuario_id") // Relaciona con Usuario
    @JsonBackReference
    private Usuario usuario;

    public Usuario getUsuario(){
        return usuario;
    }
}
