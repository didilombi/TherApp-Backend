package com.therapp.spring.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    public List<Mensaje> findByUsuarioAndTerapeuta(Usuario usuario, Terapeuta terapeuta);
}
