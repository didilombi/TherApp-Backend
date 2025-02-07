package com.therapp.spring.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {
    private int id;
    private String contenido;
    private Date fechaEnvio;
    private Boolean visto;
    private int emisorId;
    private String emisorNombre;
    private int receptorId;
    private String receptorNombre;
    private String archivoUrl;

}