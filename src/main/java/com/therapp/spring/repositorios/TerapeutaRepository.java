package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Terapeuta;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Integer> {
    
}
