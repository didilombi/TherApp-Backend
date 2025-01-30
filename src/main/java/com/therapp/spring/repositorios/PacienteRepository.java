package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    
}
