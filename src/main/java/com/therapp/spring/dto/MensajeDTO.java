package com.therapp.spring.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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

    public MensajeDTO(int id, String contenido, Date fechaEnvio, Boolean visto, int emisorId, String emisorNombre, int receptorId, String receptorNombre, String archivoUrl) {
        this.id = id;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
        this.visto = visto;
        this.emisorId = emisorId;
        this.emisorNombre = emisorNombre;
        this.receptorId = receptorId;
        this.receptorNombre = receptorNombre;  
        this.archivoUrl = archivoUrl;
    }
}
