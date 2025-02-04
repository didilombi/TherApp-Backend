package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;


import java.util.Optional;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Integer> {
    Optional<Terapeuta> findByUsuario(Usuario usuario);

    
}
