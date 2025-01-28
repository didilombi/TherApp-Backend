package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.UsuarioPublicacion;

public interface UsuarioPublicacionRepository extends JpaRepository<UsuarioPublicacion, Long> {
}
