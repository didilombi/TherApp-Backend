package com.therapp.spring.controladores;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.therapp.spring.modelo.MultimediaMensaje;
import com.therapp.spring.servicios.MultimediaMensajeService;

@RestController
@RequestMapping("/api/multimedia")
public class MultimediaMensajeController {

    @Autowired
    private MultimediaMensajeService multimediaMensajeService;

    // Endpoint para subir un archivo
    @PostMapping("/upload/{mensajeId}")
    public ResponseEntity<MultimediaMensaje> uploadFile(@PathVariable Long mensajeId, @RequestParam("archivo") MultipartFile file) {
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
