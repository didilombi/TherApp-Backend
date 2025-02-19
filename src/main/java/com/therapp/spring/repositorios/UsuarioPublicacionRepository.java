package com.therapp.spring.repositorios;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.modelo.UsuarioPublicacion;

public interface UsuarioPublicacionRepository extends JpaRepository<UsuarioPublicacion, Long> {
    List<UsuarioPublicacion> findByUsuario(Usuario usuario);
    List<UsuarioPublicacion> findByPublicacion(Publicacion publicacion);
    Optional<UsuarioPublicacion> findByPublicacionAndUsuario(Publicacion publicacion, Usuario usuario);
    void deleteByUsuarioId(Long usuarioId);
    @Query("SELECT up FROM UsuarioPublicacion up WHERE up.usuario.id = :usuarioId")
    List<UsuarioPublicacion> findByUsuarioId(@Param("usuarioId") Long usuarioId);
    @Query("SELECT up.publicacion.id FROM UsuarioPublicacion up WHERE up.usuario.id = :usuarioId")
    List<Long> findPublicacionIdsByUsuarioId(@Param("usuarioId") Long usuarioId);

}
