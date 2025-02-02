package com.therapp.spring.modelo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String contenido;
    private Date fechaEnvio;
    private Boolean visto;

    @OneToMany(mappedBy = "mensaje")
    private List<MultimediaMensaje> multimediaMensajes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "terapeuta_id")
    @JsonManagedReference  // Esta anotación evita la recursión infinita en la propiedad 'terapeuta'    
    private Terapeuta terapeuta;
}
