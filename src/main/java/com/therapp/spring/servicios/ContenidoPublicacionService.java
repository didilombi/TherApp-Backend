package com.therapp.spring.servicios;

import com.therapp.spring.modelo.ContenidoPublicacion;
import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.repositorios.ContenidoPublicacionRepository;
import com.therapp.spring.repositorios.PublicacionRepository;
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
import java.util.UUID;

@Service
public class ContenidoPublicacionService {

    private final Path rootLocation = Paths.get("uploads"); // Carpeta donde se guardan los archivos

    @Autowired
    private ContenidoPublicacionRepository contenidoPublicacionRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;

    // Guardar archivo en el servidor y la base de datos
    public ContenidoPublicacion saveFile(MultipartFile file, Long publicacionId) {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), rootLocation.resolve(fileName));

            ContenidoPublicacion contenidoPublicacion = new ContenidoPublicacion();
            contenidoPublicacion.setTipo(file.getContentType().startsWith("image") ? "foto" : "video");
            contenidoPublicacion.setUrl(fileName);

            Publicacion publicacion = publicacionRepository.findById(publicacionId)
                    .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
            contenidoPublicacion.setPublicacion(publicacion);

            return contenidoPublicacionRepository.save(contenidoPublicacion);

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo: " + e.getMessage());
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
                throw new RuntimeException("No se pudo leer el archivo: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al cargar el archivo: " + e.getMessage());
        }
    }
}
