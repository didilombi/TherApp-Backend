package com.therapp.spring.servicios;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.ComentarioPublicacion;
import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.ComentarioPublicacionRepository;
import com.therapp.spring.repositorios.PublicacionRepository;
import com.therapp.spring.repositorios.UsuarioRepository;

@Service
public class ComentarioPublicacionService {

    private final ComentarioPublicacionRepository comentarioPublicacionRepository;
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ComentarioPublicacionService(ComentarioPublicacionRepository comentarioPublicacionRepository, PublicacionRepository publicacionRepository, UsuarioRepository usuarioRepository) {
        this.comentarioPublicacionRepository = comentarioPublicacionRepository;
        this.publicacionRepository = publicacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ComentarioPublicacion agregarComentario(Long publicacionId, Long usuarioId, String contenido) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        ComentarioPublicacion comentario = new ComentarioPublicacion();
        comentario.setPublicacion(publicacion);
        comentario.setUsuario(usuario);
        comentario.setContenido(contenido);
        comentario.setFecha(new Date()); // Establecer la fecha y hora del comentario
        return comentarioPublicacionRepository.save(comentario);
    }

    public ComentarioPublicacion agregarRespuesta(Long comentarioPadreId, Long usuarioId, String contenido) {
        ComentarioPublicacion comentarioPadre = comentarioPublicacionRepository.findById(comentarioPadreId)
                .orElseThrow(() -> new IllegalArgumentException("Comentario padre no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        ComentarioPublicacion respuesta = new ComentarioPublicacion();
        respuesta.setComentarioPadre(comentarioPadre);
        respuesta.setPublicacion(comentarioPadre.getPublicacion());
        respuesta.setUsuario(usuario);
        respuesta.setContenido(contenido);
        respuesta.setFecha(new Date()); // Establecer la fecha y hora de la respuesta
        return comentarioPublicacionRepository.save(respuesta);
    }

    public void eliminarComentario(Long comentarioId) {
        comentarioPublicacionRepository.deleteById(comentarioId);
    }
}
