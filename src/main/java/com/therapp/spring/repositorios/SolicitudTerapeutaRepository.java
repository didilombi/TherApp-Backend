package com.therapp.spring.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.SolicitudTerapeuta;
import com.therapp.spring.modelo.Usuario;

public interface SolicitudTerapeutaRepository extends JpaRepository<SolicitudTerapeuta, Long>{
    List<SolicitudTerapeuta> findAll();

    SolicitudTerapeuta findByUsuario(Usuario u);
    
    SolicitudTerapeuta findByEmail(String email);
}
