package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;

import java.util.Optional;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Long> {
    Optional<Terapeuta> findByUsuario(Usuario usuario);

    @Modifying
    @Query("DELETE FROM Terapeuta t WHERE t.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);
}
