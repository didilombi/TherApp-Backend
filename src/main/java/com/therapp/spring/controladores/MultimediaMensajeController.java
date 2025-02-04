package com.therapp.spring.controladores;

import com.therapp.spring.modelo.MultimediaMensaje;
import com.therapp.spring.servicios.MultimediaMensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/multimedia")
public class MultimediaMensajeController {

    @Autowired
    private MultimediaMensajeService multimediaMensajeService;

    // Endpoint para subir un archivo
    @PostMapping("/upload/{mensajeId}")
    public ResponseEntity<MultimediaMensaje> uploadFile(@PathVariable int mensajeId, @RequestParam("archivo") MultipartFile file) {
        MultimediaMensaje multimediaMensaje = multimediaMensajeService.saveFile(file, mensajeId);
        return ResponseEntity.ok(multimediaMensaje);
    }

    // Endpoint para obtener un archivo
    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = multimediaMensajeService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(file);
    }
}
