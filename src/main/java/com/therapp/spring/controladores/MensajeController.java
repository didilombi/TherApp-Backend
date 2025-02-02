package com.therapp.spring.controladores;

import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.servicios.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    //APARTADO DE MENSAJES DEL CHAT ENTRE USUARIO Y TERAPEUTA
    // Obtener mensajes entre un usuario y terapeuta
    @GetMapping("/{usuarioId}/{terapeutaId}")
    public List<Mensaje> obtenerMensajes(@PathVariable Integer usuarioId, @PathVariable Integer terapeutaId) {
        return mensajeService.obtenerMensajes(usuarioId, terapeutaId); // Llamada al servicio para obtener los mensajes
    }

    // Enviar mensaje de un usuario a un terapeuta
    @PostMapping("/{usuarioId}/{terapeutaId}")
    public Mensaje enviarMensaje(@PathVariable Integer usuarioId, @PathVariable Integer terapeutaId, @RequestBody String contenido) {
        return mensajeService.enviarMensaje(usuarioId, terapeutaId, contenido);
    }
}
