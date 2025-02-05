package com.therapp.spring.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MensajeDTO {
    private Long id;
    private String contenido;
    private Date fechaEnvio;
    private Boolean visto;
    private Long emisorId;
    private String emisorNombre;
    private Long receptorId;
    private String receptorNombre;
    private String archivoUrl;

    public MensajeDTO(Long id, String contenido, Date fechaEnvio, Boolean visto, Long emisorId, String emisorNombre, Long receptorId, String receptorNombre, String archivoUrl) {
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
