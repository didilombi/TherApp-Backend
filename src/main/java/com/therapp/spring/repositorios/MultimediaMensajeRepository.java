package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.MultimediaMensaje;

public interface MultimediaMensajeRepository extends JpaRepository<MultimediaMensaje, Integer> {

}