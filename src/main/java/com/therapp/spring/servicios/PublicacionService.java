package com.therapp.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.ContenidoPublicacion;
import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.RolPublicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.modelo.UsuarioPublicacion;
import com.therapp.spring.repositorios.ContenidoPublicacionRepository;
import com.therapp.spring.repositorios.PublicacionRepository;
import com.therapp.spring.repositorios.UsuarioPublicacionRepository;
import com.therapp.spring.repositorios.UsuarioRepository;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final UsuarioPublicacionRepository usuarioPublicacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final ContenidoPublicacionRepository contenidoPublicacionRepository;

    @Autowired
    public PublicacionService(PublicacionRepository publicacionRepository, UsuarioPublicacionRepository usuarioPublicacionRepository, UsuarioRepository usuarioRepository, ContenidoPublicacionRepository contenidoPublicacionRepository) {
        this.publicacionRepository = publicacionRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioPublicacionRepository = usuarioPublicacionRepository;
        this.contenidoPublicacionRepository = contenidoPublicacionRepository;
    }

    public List<Publicacion> findAll() {
        return publicacionRepository.findAll();
    }

    public Publicacion findById(Long id) {
        return publicacionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada"));
    }

    public Publicacion guardarPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public Publicacion crearPublicacion(Usuario usuario, Publicacion publicacion, List<ContenidoPublicacion> contenidos, RolPublicacion rol) {
        if (contenidos.size() > 10) {
            throw new IllegalStateException("La publicación no puede tener más de 10 contenidos multimedia.");
        }

        Publicacion nuevaPublicacion = publicacionRepository.saveAndFlush(publicacion);

        UsuarioPublicacion usuarioPublicacion = new UsuarioPublicacion();
        usuarioPublicacion.setUsuario(usuario);
        usuarioPublicacion.setPublicacion(nuevaPublicacion);
        usuarioPublicacion.setRol(rol);
        usuarioPublicacionRepository.save(usuarioPublicacion);

        for (ContenidoPublicacion contenido : contenidos) {
            contenido.setPublicacion(nuevaPublicacion);
            contenidoPublicacionRepository.save(contenido);
        }

        nuevaPublicacion.setContenidos(contenidos);
        return nuevaPublicacion;
    }

    public Publicacion update(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public void deleteById(Long id) {
        publicacionRepository.deleteById(id);
    }

    public void agregarColaborador(Publicacion publicacion, Usuario usuario) {
        List<UsuarioPublicacion> relaciones = usuarioPublicacionRepository.findByPublicacion(publicacion);

        boolean existeAutor = relaciones.stream().anyMatch(up -> up.getRol() == RolPublicacion.AUTOR);
        if (!existeAutor) {
            throw new IllegalStateException("La publicación no tiene un autor asignado.");
        }

        long numeroColaboradores = relaciones.stream().filter(up -> up.getRol() == RolPublicacion.COLABORADOR).count();
        if (numeroColaboradores >= 3) {
            throw new IllegalStateException("La publicación ya tiene el máximo de 3 colaboradores.");
        }

        UsuarioPublicacion usuarioPublicacion = new UsuarioPublicacion();
        usuarioPublicacion.setUsuario(usuario);
        usuarioPublicacion.setPublicacion(publicacion);
        usuarioPublicacion.setRol(RolPublicacion.COLABORADOR);

        usuarioPublicacionRepository.save(usuarioPublicacion);
    }

    public void eliminarColaborador(Publicacion publicacion, Usuario usuario) {
        UsuarioPublicacion usuarioPublicacion = usuarioPublicacionRepository.findByPublicacionAndUsuario(publicacion, usuario)
                .orElseThrow(() -> new IllegalArgumentException("El colaborador no está asociado a esta publicación"));

        if (usuarioPublicacion.getRol() != RolPublicacion.COLABORADOR) {
            throw new IllegalStateException("Solo se pueden eliminar colaboradores");
        }

        usuarioPublicacionRepository.delete(usuarioPublicacion);
    }

    public void agregarContenido(Publicacion publicacion, ContenidoPublicacion contenido) {
        if (publicacion.getContenidos().size() >= 10) {
            throw new IllegalStateException("La publicación ya tiene el máximo de 10 contenidos multimedia.");
        }
        contenido.setPublicacion(publicacion);
        contenidoPublicacionRepository.save(contenido);
    }

    public void eliminarContenido(Publicacion publicacion, Long contenidoId) {
        ContenidoPublicacion contenido = contenidoPublicacionRepository.findById(contenidoId)
                .orElseThrow(() -> new IllegalArgumentException("Contenido no encontrado"));

        if (!contenido.getPublicacion().equals(publicacion)) {
            throw new IllegalStateException("El contenido no pertenece a esta publicación");
        }

        contenidoPublicacionRepository.delete(contenido);
    }
}
