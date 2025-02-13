package com.therapp.spring.modelo;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaPublicacion;
    private String ruta;

    @ManyToMany
    @JoinTable(
        name = "video_categoria",
        joinColumns = @JoinColumn(name = "videoId"),
        inverseJoinColumns = @JoinColumn(name = "categoriaId")
    )
    private Set<CategoriaVideos> categorias;

    
}
