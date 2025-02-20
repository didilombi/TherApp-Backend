package com.therapp.spring.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.UsuarioPublicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

    List<Publicacion> findByUsuarios(List<UsuarioPublicacion> u);

    @Query("SELECT c.tipo FROM ContenidoPublicacion c WHERE c.publicacion.id = :publicacionId")
    String findTipoByPublicacionId(@Param("publicacionId") Long publicacionId);

    @Query("SELECT c.url FROM ContenidoPublicacion c WHERE c.publicacion.id = :publicacionId")
    String findUrlByPublicacionId(@Param("publicacionId") Long publicacionId);
}
