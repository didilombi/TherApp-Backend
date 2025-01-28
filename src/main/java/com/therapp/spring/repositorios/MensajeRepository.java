package com.therapp.spring.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    
}
