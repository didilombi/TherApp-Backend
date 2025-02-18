package com.therapp.spring.servicios;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.LikeComentario;
import com.therapp.spring.modelo.ComentarioPublicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.LikeComentarioRepository;
import com.therapp.spring.repositorios.ComentarioPublicacionRepository;
import com.therapp.spring.repositorios.UsuarioRepository;

@Service
public class LikeComentarioService {

    private final LikeComentarioRepository likeComentarioRepository;
    private final ComentarioPublicacionRepository comentarioPublicacionRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LikeComentarioService(LikeComentarioRepository likeComentarioRepository, ComentarioPublicacionRepository comentarioPublicacionRepository, UsuarioRepository usuarioRepository) {
        this.likeComentarioRepository = likeComentarioRepository;
        this.comentarioPublicacionRepository = comentarioPublicacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void darLike(Long comentarioId, Long usuarioId) {
        ComentarioPublicacion comentario = comentarioPublicacionRepository.findById(comentarioId)
                .orElseThrow(() -> new IllegalArgumentException("Comentario no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (likeComentarioRepository.findByUsuarioAndComentario(usuario, comentario).isPresent()) {
            throw new IllegalStateException("El usuario ya ha dado like a este comentario");
        }

        LikeComentario like = new LikeComentario();
        like.setComentario(comentario);
        like.setUsuario(usuario);
        like.setFecha(new Date()); // Establecer la fecha y hora del like
        likeComentarioRepository.save(like);
    }

    public void quitarLike(Long comentarioId, Long usuarioId) {
        ComentarioPublicacion comentario = comentarioPublicacionRepository.findById(comentarioId)
                .orElseThrow(() -> new IllegalArgumentException("Comentario no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        LikeComentario like = likeComentarioRepository.findByUsuarioAndComentario(usuario, comentario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no ha dado like a este comentario"));

        likeComentarioRepository.delete(like);
    }
}
