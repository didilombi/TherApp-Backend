package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Seguidor;

public interface SeguidorRepository extends JpaRepository<Seguidor, Integer> {
    
}
