package com.therapp.spring.modelo;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cif;
    private String direccion;
    private String telefono;
    private String email;
    private String descripcion;
    private String sitioweb;

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<Terapeuta> terapeutas = new ArrayList<>();

    public Organizacion(String cif, String direccion, String telefono, String email, String descripcion, String sitioweb) {
        this.cif = cif;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.descripcion = descripcion;
        this.sitioweb = sitioweb;
    }
    
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; 
}
