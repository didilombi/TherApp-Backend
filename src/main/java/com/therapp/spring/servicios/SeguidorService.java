package com.therapp.spring.servicios;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.therapp.spring.modelo.Seguidor;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.SeguidorRepository;
import com.therapp.spring.repositorios.UsuarioRepository;

@Service
public class SeguidorService {

        private final SeguidorRepository seguidorRepository;
        private final UsuarioRepository usuarioRepository;

    @Autowired
    public SeguidorService(SeguidorRepository seguidorRepository, UsuarioRepository usuarioRepository) {
        this.seguidorRepository = seguidorRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void seguirUsuario(Long seguidorId, Long seguidoId) {
        Usuario seguidor = usuarioRepository.findById(seguidorId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario seguidor no encontrado"));
        Usuario seguido = usuarioRepository.findById(seguidoId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario seguido no encontrado"));

        if (seguidorRepository.findByUsuarioSeguidorAndUsuarioSeguido(seguidor, seguido).isPresent()) {
            throw new IllegalStateException("El usuario ya sigue a este usuario");
        }

        Seguidor seguidorEntity = new Seguidor();
        seguidorEntity.setUsuarioSeguidor(seguidor);
        seguidorEntity.setUsuarioSeguido(seguido);
        seguidorEntity.setFechaSeguimiento(new Date());
        seguidorRepository.save(seguidorEntity);
    }

    public void dejarDeSeguirUsuario(Long seguidorId, Long seguidoId) {
        Usuario seguidor = usuarioRepository.findById(seguidorId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario seguidor no encontrado"));
        Usuario seguido = usuarioRepository.findById(seguidoId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario seguido no encontrado"));

        Seguidor seguidorEntity = seguidorRepository.findByUsuarioSeguidorAndUsuarioSeguido(seguidor, seguido)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no sigue a este usuario"));

        seguidorRepository.delete(seguidorEntity);
    }

    public List<Usuario> obtenerSeguidoresComunes(Long usuarioId, Long buscadoId) {
        List<Usuario> seguidosUsuario = seguidorRepository.findSeguidosByUsuarioId(usuarioId);
        List<Usuario> seguidoresBuscado = seguidorRepository.findSeguidoresByUsuarioId(buscadoId);

        return seguidosUsuario.stream()
                .filter(seguidoresBuscado::contains)
                .collect(Collectors.toList());
    }

    public Boolean estaSiguiendo(Long seguidorId, Long seguidoId) {
        Usuario seguidor = usuarioRepository.findById(seguidorId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario seguidor no encontrado"));
        Usuario seguido = usuarioRepository.findById(seguidoId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario seguido no encontrado"));

        return seguidorRepository.findByUsuarioSeguidorAndUsuarioSeguido(seguidor, seguido).isPresent();
    }
}