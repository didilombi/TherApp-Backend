package com.therapp.spring.modelo;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LikePublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "publicacionId")
    private Publicacion publicacion;
}
