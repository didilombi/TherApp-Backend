package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.ContenidoPublicacion;

public interface ContenidoPublicacionRepository extends JpaRepository<ContenidoPublicacion, Long> {
}
