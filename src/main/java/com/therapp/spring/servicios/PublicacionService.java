package com.therapp.spring.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.modelo.UsuarioPublicacion;
import com.therapp.spring.modelo.RolPublicacion;
import com.therapp.spring.repositorios.PublicacionRepository;
import com.therapp.spring.repositorios.UsuarioRepository;
import com.therapp.spring.repositorios.UsuarioPublicacionRepository;

@Service
public class PublicacionService     {

    private final PublicacionRepository publicacionRepository;
    private final UsuarioPublicacionRepository usuarioPublicacionRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public PublicacionService(PublicacionRepository publicacionRepository, UsuarioPublicacionRepository usuarioPublicacionRepository, UsuarioRepository usuarioRepository) {
        this.publicacionRepository = publicacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioPublicacionRepository = usuarioPublicacionRepository;
    }

    public Publicacion guardarPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public Publicacion crearPublicacion(Usuario usuario, Publicacion publicacion, RolPublicacion rol) {
        // Guardar la publicación y obtenerla con la id generada
        Publicacion nuevaPublicacion = publicacionRepository.saveAndFlush(publicacion);
    
        // Crear la relación entre usuario y publicación
        UsuarioPublicacion usuarioPublicacion = new UsuarioPublicacion();
        usuarioPublicacion.setUsuario(usuario);
        usuarioPublicacion.setPublicacion(nuevaPublicacion);
        usuarioPublicacion.setRol(rol);
    
        // Guardar la relación
        usuarioPublicacionRepository.save(usuarioPublicacion);
    
        return nuevaPublicacion;
    }
    
}
