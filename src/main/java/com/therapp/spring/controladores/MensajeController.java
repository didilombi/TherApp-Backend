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
    public ResponseEntity<List<Mensaje>> obtenerChat(@PathVariable Integer id1, @PathVariable Integer id2) {
        List<Mensaje> mensajes = mensajeService.obtenerChat(id1, id2);

        if (mensajes == null || mensajes.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList()); // ✅ Devuelve una lista vacía en JSON si no hay mensajes
        }

        return ResponseEntity.ok(mensajes); // ✅ Devuelve la lista de mensajes correctamente
    }

    // POST: enviar mensaje de id1 -> id2
    @PostMapping("/chat/{id1}/{id2}")
    public Mensaje enviarMensaje(@PathVariable Integer id1,
                                 @PathVariable Integer id2,
                                 @RequestBody MensajeDTO dto) {
        return mensajeService.enviarMensaje(id1, id2, dto.getContenido());
    }
}
