package com.therapp.spring.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellidos;
    private String nombreUsuario;
    private String email;
    private String clave;
    private String fotoPerfil;
    private String rol;
    private String dni;
    private Date fechaNacimiento;
    private String telefono;
    private Date fechaRegistro;
    private String ubicacion;
    private String biografia;







}
