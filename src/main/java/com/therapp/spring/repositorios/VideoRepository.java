package com.therapp.spring.repositorios;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.CategoriaVideos;
import com.therapp.spring.modelo.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

    Optional<Video> findByTitulo(String titulo);

    List<Video> findByCategorias(CategoriaVideos categoria);

    
    
}
