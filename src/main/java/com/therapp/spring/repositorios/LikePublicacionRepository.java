package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.LikePublicacion;
import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.Usuario;

import java.util.Optional;

public interface LikePublicacionRepository extends JpaRepository<LikePublicacion, Integer> {
    Optional<LikePublicacion> findByUsuarioAndPublicacion(Usuario usuario, Publicacion publicacion);
}
