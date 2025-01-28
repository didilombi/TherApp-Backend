package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.therapp.spring.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
