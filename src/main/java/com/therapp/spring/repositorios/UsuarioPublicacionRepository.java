package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.UsuarioPublicacion;
import com.therapp.spring.modelo.Usuario;
import java.util.List;


public interface UsuarioPublicacionRepository extends JpaRepository<UsuarioPublicacion, Long> {


}
