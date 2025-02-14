package com.therapp.spring.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.therapp.spring.modelo.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

    @Query("SELECT m FROM Mensaje m " +
       "WHERE (m.emisor.id = :user1 AND m.receptor.id = :user2) " +
       "OR (m.emisor.id = :user2 AND m.receptor.id = :user1) " +
       "ORDER BY m.fechaEnvio ASC")
    List<Mensaje> findChatBetweenUsers(@Param("user1") Long user1, @Param("user2") Long user2);

    @Query("SELECT DISTINCT CASE WHEN m.emisor.id = :usuarioId THEN m.receptor.id ELSE m.emisor.id END " +
           "FROM Mensaje m WHERE m.emisor.id = :usuarioId OR m.receptor.id = :usuarioId")
    List<Long> findConversacionesByUsuarioId(@Param("usuarioId") Long usuarioId);
}