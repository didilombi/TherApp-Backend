package com.therapp.spring.controladores;

import com.therapp.spring.dto.MensajeDTO;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.servicios.MensajeService;
import com.therapp.spring.servicios.MultimediaMensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private MultimediaMensajeService multimediaMensajeService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // GET: listar mensajes entre 2 usuarios
    @GetMapping("/chat/{id1}/{id2}")
    public ResponseEntity<List<MensajeDTO>> obtenerChat(@PathVariable Integer id1, @PathVariable Integer id2) {
        List<MensajeDTO> mensajes = mensajeService.obtenerChat(id1, id2);
        return ResponseEntity.ok(mensajes);
    }

    // POST: enviar mensaje de id1 -> id2
    @PostMapping("/chat/{id1}/{id2}")
    public ResponseEntity<MensajeDTO> enviarMensaje(
            @PathVariable Integer id1,
            @PathVariable Integer id2,
            @RequestParam(value = "contenido", required = false) String contenido,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo) {
    
        String archivoUrl = null;
    
        if (archivo != null && !archivo.isEmpty()) {
            archivoUrl = multimediaMensajeService.saveFile(archivo, id1).getUrl();
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
    
        // ✅ Enviar mensaje en tiempo real al canal correspondiente
        messagingTemplate.convertAndSend("/topic/chat/" + id2, mensajeDTO);
    
        return ResponseEntity.ok(mensajeDTO);
    }
}
