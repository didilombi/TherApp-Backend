package com.therapp.spring.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.therapp.spring.modelo.ComentarioPublicacion;
import com.therapp.spring.modelo.LikeComentario;
import com.therapp.spring.modelo.Usuario;

public interface LikeComentarioRepository extends JpaRepository<LikeComentario, Long> {
    Optional<LikeComentario> findByUsuarioAndComentario(Usuario usuario, ComentarioPublicacion comentario);
}
