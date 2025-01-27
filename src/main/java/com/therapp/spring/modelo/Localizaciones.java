package com.therapp.spring.modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Localizaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pais;
    private String ciudad;
    private String provincia;
    private String municipio;
    private String codigoPostal;
    private String direccion;

}
