package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.CategoriaVideos;

public interface CategoriaRepository extends JpaRepository<CategoriaVideos, Long>{
    
    CategoriaVideos findByNombre(String nombre);

}
