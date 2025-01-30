package com.therapp.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Autowired
    private TerapeutaService terapeutaService;
    
    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder; // Codificador de contraseñas

    public List<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    //este metodo guarda un usuario en la base de datos
    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    //este metodo se encarga de guardar una lista de usuarios predefinidos en la base de datos
    public void saveAll(Iterable<Usuario> usuarios) {

        usuarioRepositorio.saveAll(usuarios);
    }

    public void delete(Usuario u) {
        terapeutaService.deleteByUsuario(u);
        usuarioRepositorio.delete(u);
    }

    public void deleteById(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
        
    }

   
}
