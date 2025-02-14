package com.therapp.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.therapp.spring.dto.MensajeDTO;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.MensajeService;
import com.therapp.spring.servicios.MultimediaMensajeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private MultimediaMensajeService multimediaMensajeService;

    // GET: listar mensajes entre 2 usuarios
    @GetMapping("/chat/{id1}/{id2}")
    public ResponseEntity<List<MensajeDTO>> obtenerChat(@PathVariable Long id1, @PathVariable Long id2) {
        List<MensajeDTO> mensajes = mensajeService.obtenerChat(id1, id2);
        return ResponseEntity.ok(mensajes);
    }

    // POST: enviar mensaje de id1 -> id2
    @PostMapping(value = "/chat/{id1}/{id2}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MensajeDTO> enviarMensaje(
        @PathVariable Long id1,
        @PathVariable Long id2,
        @RequestParam(value = "contenido", required = false) String contenido,
        @RequestParam(value = "archivo", required = false) MultipartFile archivo) {

        String archivoUrl = null;

        if (archivo != null && !archivo.isEmpty()) {
            archivoUrl = multimediaMensajeService.saveFile(archivo, id1).getUrl(); // ✅ Guardar archivo y obtener la URL
        }

        Mensaje nuevoMensaje = mensajeService.enviarMensaje(id1, id2, contenido, archivoUrl);

        MensajeDTO mensajeDTO = new MensajeDTO(
            nuevoMensaje.getId(),
            nuevoMensaje.getContenido(),
            nuevoMensaje.getFechaEnvio(),
            nuevoMensaje.getVisto(),
            nuevoMensaje.getEmisor().getId(),
            nuevoMensaje.getEmisor().getNombre(),
            nuevoMensaje.getReceptor().getId(),
            nuevoMensaje.getReceptor().getNombre(),
            nuevoMensaje.getArchivoUrl()
        );

        return ResponseEntity.ok(mensajeDTO);
    }

    @GetMapping("/conversaciones/{usuarioId}")
    public ResponseEntity<List<Usuario>> obtenerConversaciones(@PathVariable Long usuarioId) {
        List<Usuario> conversaciones = mensajeService.obtenerConversaciones(usuarioId);
        return ResponseEntity.ok(conversaciones);
    }
}