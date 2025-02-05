package com.therapp.spring.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.UsuarioPublicacionRepository;
import com.therapp.spring.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepositorio;
    private final UsuarioPublicacionRepository usuarioPublicacionRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepositorio, UsuarioPublicacionRepository usuarioPublicacionRepository) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioPublicacionRepository = usuarioPublicacionRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepositorio.findById(id);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    //este metodo se encarga de guardar una lista de usuarios predefinidos en la base de datos
    public void saveAll(Iterable<Usuario> usuarios) {
        usuarioRepositorio.saveAll(usuarios);
    }

    public void delete(Usuario u) {
        usuarioRepositorio.delete(u);
    }

    @Transactional
    public void deleteById(Long id) {
        // Eliminar referencias en otras tablas
        usuarioPublicacionRepository.deleteByUsuarioId(id);
        // Eliminar el usuario
        usuarioRepositorio.deleteById(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepositorio.findByNombreUsuario(username);
    }


}
