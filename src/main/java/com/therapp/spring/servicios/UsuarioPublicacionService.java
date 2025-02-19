package com.therapp.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.UsuarioPublicacion;
import com.therapp.spring.repositorios.UsuarioPublicacionRepository;

@Service
public class UsuarioPublicacionService {
	
	@Autowired
	UsuarioPublicacionRepository usuarioPublicacionRepository;
	
	public List<UsuarioPublicacion> buscarPublicacionesPorUsuario(Long usuarioId) {
        return usuarioPublicacionRepository.findByUsuarioId(usuarioId);
    }
	
	public List<Long> obtenerPublicacionIdsPorUsuario(Long usuarioId) {
        return usuarioPublicacionRepository.findPublicacionIdsByUsuarioId(usuarioId);
    }
}
