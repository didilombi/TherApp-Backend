package com.therapp.spring.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.therapp.spring.modelo.Seguidor;
import com.therapp.spring.modelo.Usuario;

public interface SeguidorRepository extends JpaRepository<Seguidor, Long> {
    Optional<Seguidor> findByUsuarioSeguidorAndUsuarioSeguido(Usuario usuarioSeguidor, Usuario usuarioSeguido);
}
