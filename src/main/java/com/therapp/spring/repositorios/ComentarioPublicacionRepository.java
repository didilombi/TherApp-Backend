package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.ComentarioPublicacion;

public interface ComentarioPublicacionRepository extends JpaRepository<ComentarioPublicacion,Integer>{
    
}
