package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
	
}
