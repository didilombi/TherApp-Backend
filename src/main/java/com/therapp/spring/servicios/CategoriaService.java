package com.therapp.spring.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.therapp.spring.modelo.CategoriaVideos;
import com.therapp.spring.repositorios.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository categoriaRepository;

    public void save(CategoriaVideos cat){
        categoriaRepository.save(cat);
    }
}
