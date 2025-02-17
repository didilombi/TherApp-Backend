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
import com.therapp.spring.modelo.MultimediaMensaje;
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
    public ResponseEntity<?> enviarMensaje(
        @PathVariable("id1") Long id1,  // 👈 Asegurarse de que los nombres sean explícitos
        @PathVariable("id2") Long id2,
        @RequestParam(value = "contenido", required = false) String contenido,
        @RequestParam(value = "archivo", required = false) MultipartFile archivo) {

        System.out.println("📩 Enviando mensaje...");
        System.out.println("🆔 ID Emisor: " + id1);
        System.out.println("🆔 ID Receptor: " + id2);
        System.out.println("📝 Contenido: " + contenido);
        System.out.println("📂 Archivo: " + (archivo != null ? archivo.getOriginalFilename() : "Ninguno"));            

        // 1️⃣ Primero, guardar el mensaje sin archivo
        Mensaje nuevoMensaje = mensajeService.enviarMensaje(id1, id2, contenido, null);

        if (nuevoMensaje == null) {
            System.out.println("❌ Error: El mensaje no se creó correctamente.");
            return ResponseEntity.status(500).body("Error al crear el mensaje");
        }

        // 2️⃣ Ahora, guardar el archivo multimedia si existe
        if (archivo != null && !archivo.isEmpty()) {
            try {
                MultimediaMensaje multimediaMensaje = multimediaMensajeService.saveFile(archivo, nuevoMensaje.getId());
                nuevoMensaje.setArchivoUrl(multimediaMensaje.getUrl());  // Actualizar el mensaje con la URL del archivo
                mensajeService.save(nuevoMensaje); // Guardar el mensaje actualizado con la URL
                System.out.println("✅ Archivo guardado en: " + multimediaMensaje.getUrl());
            } catch (Exception e) {
                System.out.println("❌ Error al guardar el archivo: " + e.getMessage());
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

        System.out.println("📨 Mensaje enviado con éxito: " + mensajeDTO);
        return ResponseEntity.ok(mensajeDTO);
    }

    @GetMapping("/conversacion")
    public ResponseEntity<List<Mensaje>> obtenerMensajes(
            @RequestParam(name = "usuarioId") Long usuarioId,
            @RequestParam(name = "receptorId") Long receptorId) {
        
        List<Mensaje> mensajes = mensajeService.obtenerMensajes(usuarioId, receptorId);
        return ResponseEntity.ok(mensajes);
    }

}