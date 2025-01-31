package com.therapp.spring.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.modelo.UsuarioPublicacion;

public interface UsuarioPublicacionRepository extends JpaRepository<UsuarioPublicacion, Long> {
    List<UsuarioPublicacion> findByUsuario(Usuario usuario);
    List<UsuarioPublicacion> findByPublicacion(Publicacion publicacion);
    Optional<UsuarioPublicacion> findByPublicacionAndUsuario(Publicacion publicacion, Usuario usuario);
}
