package com.therapp.spring.servicios;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.therapp.spring.dto.UsuarioDTO;
import com.therapp.spring.dto.TerapeutaDTo;
import com.therapp.spring.dto.UsuarioDTO;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Organizacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;


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

    // public void delete(Usuario u) {
    //     terapeutaService.deleteByUsuario(u);
    //     usuarioRepositorio.delete(u);
    // }

    public void deleteById(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
        
    }

    //metodo para recibir 
    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario;

        switch (usuarioDTO.getRol().toString().toUpperCase()) {
            case "null":
                usuario = new Usuario();
                break;
            case "TERAPEUTA":
                Terapeuta terapeuta = new Terapeuta();
                terapeuta.setEspecialidad(usuarioDTO.getEspecialidad());
                usuario = terapeuta;
                break;
            case "ORGANIZACION":
                Organizacion organizacion = new Organizacion();
                organizacion.setCif(usuarioDTO.getCif());
                usuario = organizacion;
                break;
            default:
                usuario = new Usuario(); // Usuario genérico
        }

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setClave(usuarioDTO.getClave()); // Ojo: Encriptar antes de guardar
        return usuarioRepositorio.save(usuario);
    }
}
