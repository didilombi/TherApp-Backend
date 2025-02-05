package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Localizacion;

public interface LocalizacionRepository extends JpaRepository<Localizacion, Long> {
    
}
