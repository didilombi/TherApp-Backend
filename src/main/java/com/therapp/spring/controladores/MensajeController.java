package com.therapp.spring.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.therapp.spring.dto.MensajeDTO;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.modelo.MultimediaMensaje;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.MensajeService;
import com.therapp.spring.servicios.MultimediaMensajeService;

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
        List<MensajeDTO> mensajes = mensajeService.obtenerChat(id1, id2, true);
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/sinLeer/{id1}/{id2}")
    public ResponseEntity<Long> obtenerMensajesSinLeer(@PathVariable Long id1, @PathVariable Long id2) {
        List<MensajeDTO> mensajes = mensajeService.obtenerChat(id1, id2, false);
        Long cantidad = (long) 0;

        for (MensajeDTO mensajeDTO : mensajes) {
            if (mensajeDTO.getVisto() == false && mensajeDTO.getReceptorId() == id1) {
                cantidad++;
            }
        }
        
        System.out.println(id2);
        System.out.println(cantidad);

        return ResponseEntity.ok(cantidad);
    }

    // POST: enviar mensaje de id1 -> id2
    @PostMapping(value = "/chat/{id1}/{id2}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> enviarMensaje(
        @PathVariable("id1") Long id1,
        @PathVariable("id2") Long id2,
        @RequestParam(value = "contenido", required = false) String contenido,
        @RequestParam(value = "archivo", required = false) MultipartFile archivo) {

        System.out.println("📩 Enviando mensaje de " + id1 + " a " + id2);
        System.out.println("📌 Contenido recibido: " + contenido);
        System.out.println("📂 Archivo recibido: " + (archivo != null ? archivo.getOriginalFilename() : "Ninguno"));

        // ✅ Si el contenido es `null`, asignarle un texto por defecto para depuración
        if (contenido == null || contenido.trim().isEmpty()) {
            contenido = "⚠️ Mensaje vacío recibido";
        }

        Mensaje nuevoMensaje = mensajeService.enviarMensaje(id1, id2, contenido, null);

        if (nuevoMensaje == null) {
            return ResponseEntity.status(500).body("Error al crear el mensaje");
        }

        // Si hay archivo, guardarlo
        if (archivo != null && !archivo.isEmpty()) {
            try {
                MultimediaMensaje multimediaMensaje = multimediaMensajeService.saveFile(archivo, nuevoMensaje.getId());
                nuevoMensaje.setArchivoUrl(multimediaMensaje.getUrl());
                mensajeService.save(nuevoMensaje);
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error al guardar el archivo");
            }
        }

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


    @GetMapping("/conversacion")
    public ResponseEntity<List<Mensaje>> obtenerMensajes(
            @RequestParam(name = "usuarioId") Long usuarioId,
            @RequestParam(name = "receptorId") Long receptorId) {
        
        List<Mensaje> mensajes = mensajeService.obtenerMensajes(usuarioId, receptorId);
        return ResponseEntity.ok(mensajes);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/conversaciones/{usuarioId}")
    public ResponseEntity<List<Usuario>> obtenerConversaciones(@PathVariable Long usuarioId) {
        List<Usuario> conversaciones = mensajeService.obtenerConversaciones(usuarioId);
        return ResponseEntity.ok(conversaciones);
    }
}