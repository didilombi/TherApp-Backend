package com.therapp.spring.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.therapp.spring.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByTelefono(String telefono);
    List<Usuario> findByNombreContainingIgnoreCaseOrUsernameContainingIgnoreCase(String nombre, String username);

    @Query("SELECT u FROM Usuario u WHERE u.id IN (SELECT s.usuarioSeguido.id FROM Seguidor s WHERE s.usuarioSeguidor.id = :usuarioId) " +
           "AND u.id NOT IN (SELECT DISTINCT CASE WHEN m.emisor.id = :usuarioId THEN m.receptor.id ELSE m.emisor.id END FROM Mensaje m WHERE m.emisor.id = :usuarioId OR m.receptor.id = :usuarioId)")
    List<Usuario> findUsuariosSeguidosSinConversacion(@Param("usuarioId") Long usuarioId);
}
