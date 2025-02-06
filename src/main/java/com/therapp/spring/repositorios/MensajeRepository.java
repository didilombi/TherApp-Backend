package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.therapp.spring.modelo.Mensaje;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

    @Query("SELECT m FROM Mensaje m " +
       "WHERE (m.emisor.id = :user1 AND m.receptor.id = :user2) " +
       "OR (m.emisor.id = :user2 AND m.receptor.id = :user1) " +
       "ORDER BY m.fechaEnvio ASC")
    List<Mensaje> findChatBetweenUsers(@Param("user1") Integer user1, @Param("user2") Integer user2);


}