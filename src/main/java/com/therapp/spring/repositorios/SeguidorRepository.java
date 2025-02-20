package com.therapp.spring.repositorios;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.therapp.spring.modelo.Seguidor;
import com.therapp.spring.modelo.Usuario;

public interface SeguidorRepository extends JpaRepository<Seguidor, Long> {
    Optional<Seguidor> findByUsuarioSeguidorAndUsuarioSeguido(Usuario usuarioSeguidor, Usuario usuarioSeguido);

    @Query("SELECT s.usuarioSeguidor FROM Seguidor s WHERE s.usuarioSeguido.id = :usuarioId")
    List<Usuario> findSeguidoresByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT s.usuarioSeguido FROM Seguidor s WHERE s.usuarioSeguidor.id = :usuarioId")
    List<Usuario> findSeguidosByUsuarioId(@Param("usuarioId") Long usuarioId);
}
