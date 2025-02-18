package com.therapp.spring.modelo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seguidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_SEGUIDO_ID", nullable = false)
    private Usuario usuarioSeguido;

    @ManyToOne
    @JoinColumn(name = "USUARIO_SEGUIDOR_ID", nullable = false)
    private Usuario usuarioSeguidor;

    private Date fechaSeguimiento;
}
