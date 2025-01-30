package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Organizaciones;

public interface OrganizacionesRepository extends JpaRepository<Organizaciones, Integer> {
    
}
