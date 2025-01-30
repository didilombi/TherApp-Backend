package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.therapp.spring.modelo.Terapeuta;
<<<<<<< HEAD
import com.therapp.spring.modelo.Usuario;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Integer> {
    Terapeuta findByUsuario(Usuario usuario);
=======
import java.util.Optional;

public interface TerapeutaRepository extends JpaRepository<Terapeuta, Integer> {
    
    Optional<Terapeuta> findById(Integer id);
    
>>>>>>> a77ffc3 (implementacion de los DTO de terapeuta y usuario)
}
