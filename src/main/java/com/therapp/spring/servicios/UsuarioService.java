package com.therapp.spring.servicios;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.therapp.spring.dto.UsuarioDTO;
import com.therapp.spring.dto.TerapeutaDTo;
import com.therapp.spring.dto.UsuarioDTO;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepositorio;
    
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

    public void delete(Usuario u){
        usuarioRepositorio.delete(u);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
        
    }

    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) {
    Usuario usuario = new Usuario();
    usuario.setNombre(usuarioDTO.getNombre());
    usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
    usuario.setEmail(usuarioDTO.getEmail());
    usuario.setRol(null); // Asigna el rol predeterminado
    return usuarioRepositorio.save(usuario);
}
    //CODIGO DE SERGIO PARA TRANSFORMAR USUARIO EN TERAPEUTA
    // @Transactional
    // public void transform(Usuario usuarionuevo){

    //     Terapeuta t = new Terapeuta(usuarionuevo,"numero de colegiado nuevo","Apellido nuevo", "Experiencia en terapia nueva", "Especialidad X nueva", "Español, Inglés nuevo");
        
    //     this.save(t);

    // }

}
