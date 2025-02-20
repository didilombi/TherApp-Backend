package com.therapp.spring.servicios;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.therapp.spring.modelo.LikePublicacion;
import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.LikePublicacionRepository;
import com.therapp.spring.repositorios.PublicacionRepository;
import com.therapp.spring.repositorios.UsuarioRepository;

@Service
public class LikePublicacionService {

    private final LikePublicacionRepository likePublicacionRepository;
    private final PublicacionRepository publicacionRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LikePublicacionService(LikePublicacionRepository likePublicacionRepository, PublicacionRepository publicacionRepository, UsuarioRepository usuarioRepository) {
        this.likePublicacionRepository = likePublicacionRepository;
        this.publicacionRepository = publicacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void darLike(Long publicacionId, Long usuarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (likePublicacionRepository.findByUsuarioAndPublicacion(usuario, publicacion).isPresent()) {
            throw new IllegalStateException("El usuario ya ha dado like a esta publicación");
        }

        LikePublicacion like = new LikePublicacion();
        like.setPublicacion(publicacion);
        like.setUsuario(usuario);
        like.setFecha(new Date()); // Establecer la fecha y hora del like
        likePublicacionRepository.save(like);
    }

    public void quitarLike(Long publicacionId, Long usuarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        LikePublicacion like = likePublicacionRepository.findByUsuarioAndPublicacion(usuario, publicacion)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no ha dado like a esta publicación"));

        likePublicacionRepository.delete(like);
    }
}
