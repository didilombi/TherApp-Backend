package com.therapp.spring.modelo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenido;
    private Date fechaEnvio;
    private Boolean visto;
    private String archivoUrl;

    // Relación con el usuario que envía el mensaje
    @ManyToOne
    @JoinColumn(name = "emisor_id")
    @JsonIgnoreProperties({"mensajesEnviados", "mensajesRecibidos","likes,comentarios"}) // Evita bucles infinitos
    private Usuario emisor;

    // Relación con el usuario que recibe el mensaje
    @ManyToOne
    @JoinColumn(name = "receptor_id")
    @JsonIgnoreProperties({"mensajesEnviados", "mensajesRecibidos","likes,comentarios"})
    private Usuario receptor;
}