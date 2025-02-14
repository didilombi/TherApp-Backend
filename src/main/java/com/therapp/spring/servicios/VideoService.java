package com.therapp.spring.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.CategoriaVideos;
import com.therapp.spring.modelo.Video;
import com.therapp.spring.repositorios.VideoRepository;

@Service
public class VideoService {

    @Autowired
    VideoRepository videoRepository;

    public List<Video> filtrarPorCategoria(CategoriaVideos cat){
        return videoRepository.findByCategorias(cat);
    }

    public Optional<Video> buscarPorTitulo(String titulo){
        return videoRepository.findByTitulo(titulo);
    }

    public void save(Video video){
        videoRepository.save(video);
    }
    
}
