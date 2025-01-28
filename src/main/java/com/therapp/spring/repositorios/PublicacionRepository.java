package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    
}
