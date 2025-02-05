package com.therapp.spring.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.therapp.spring.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Usuario> findByNombreUsuario(String nombreUsuario); // Agregar este método
}
