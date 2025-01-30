package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Integer> {
    Terapeuta findByUsuario(Usuario usuario);
}
