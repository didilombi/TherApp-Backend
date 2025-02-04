package com.therapp.spring.controladores;

import com.therapp.spring.dto.MensajeDTO;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.servicios.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    // GET: listar mensajes entre 2 usuarios
    @GetMapping("/chat/{id1}/{id2}")
    public ResponseEntity<List<MensajeDTO>> obtenerChat(@PathVariable Integer id1, @PathVariable Integer id2) {
        List<MensajeDTO> mensajes = mensajeService.obtenerChat(id1, id2);
        return ResponseEntity.ok(mensajes);
    }

    // POST: enviar mensaje de id1 -> id2
    @PostMapping("/chat/{id1}/{id2}")
    public ResponseEntity<MensajeDTO> enviarMensaje(@PathVariable Integer id1,
                                                    @PathVariable Integer id2,
                                                    @RequestBody MensajeDTO dto) {
        Mensaje nuevoMensaje = mensajeService.enviarMensaje(id1, id2, dto.getContenido());

        if (nuevoMensaje == null) {
            return ResponseEntity.badRequest().build(); //Error 400 si no se pudo guardar
        }

        // Convertir `Mensaje` a `MensajeDTO` para evitar referencias cíclicas
        MensajeDTO mensajeDTO = new MensajeDTO(
            nuevoMensaje.getId(),
            nuevoMensaje.getContenido(),
            nuevoMensaje.getFechaEnvio(),
            nuevoMensaje.getVisto(),
            nuevoMensaje.getEmisor().getId(),
            nuevoMensaje.getEmisor().getNombre(),
            nuevoMensaje.getReceptor().getId(),
            nuevoMensaje.getReceptor().getNombre()
        );

        return ResponseEntity.ok(mensajeDTO); //Devuelve el mensaje como JSON válido
    }

}
