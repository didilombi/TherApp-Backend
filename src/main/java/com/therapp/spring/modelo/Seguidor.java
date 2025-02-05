package com.therapp.spring.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seguidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuarioSeguidoId", nullable = false)
    private Usuario usuarioSeguido;

    @ManyToOne
    @JoinColumn(name = "usuarioSeguidorId", nullable = false)
    private Usuario usuarioSeguidor;

    private Date fechaSeguimiento;
}
