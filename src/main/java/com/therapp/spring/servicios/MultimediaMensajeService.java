package com.therapp.spring.servicios;

import com.therapp.spring.modelo.MultimediaMensaje;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.repositorios.MultimediaMensajeRepository;
import com.therapp.spring.repositorios.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

@Service
public class MultimediaMensajeService {

    private final Path rootLocation = Paths.get("uploads"); // Carpeta donde se guardan los archivos

    @Autowired
    private MultimediaMensajeRepository multimediaMensajeRepository;

    @Autowired
    private MensajeRepository mensajeRepository;

    // Guardar archivo en el servidor y la base de datos
    public MultimediaMensaje saveFile(MultipartFile file, int mensajeId) {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), rootLocation.resolve(fileName));

            MultimediaMensaje multimediaMensaje = new MultimediaMensaje();
            multimediaMensaje.setTipoContenido(file.getContentType());
            multimediaMensaje.setUrl(fileName);
            multimediaMensaje.setFechaEnvio(new Date());

            Mensaje mensaje = mensajeRepository.findById(mensajeId).orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
            multimediaMensaje.setMensaje(mensaje);

            return multimediaMensajeRepository.save(multimediaMensaje);

        } catch (IOException e) {
            throw new RuntimeException("❌ Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Cargar archivo desde el servidor
    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("❌ No se pudo leer el archivo: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("❌ Error al cargar el archivo: " + e.getMessage());
        }
    }

    
}