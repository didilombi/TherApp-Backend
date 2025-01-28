package com.therapp.spring.repositorios;

import com.therapp.spring.modelo.LikeComentario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeComentarioRepository extends JpaRepository<LikeComentario, Integer>{
    
}
