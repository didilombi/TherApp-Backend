package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Organizacion;

public interface OrganizacionesRepository extends JpaRepository<Organizacion, Long> {
    
}
