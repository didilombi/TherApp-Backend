package com.therapp.spring.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.UsuarioPublicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

    List<Publicacion> findByUsuarios(List<UsuarioPublicacion> u);
}
