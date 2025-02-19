package com.therapp.spring.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.therapp.spring.dto.CreateUsuarioDTO;
import com.therapp.spring.modelo.ConfirmationToken;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.repositorios.ConfirmationTokenRepository;
import com.therapp.spring.repositorios.UsuarioPublicacionRepository;
import com.therapp.spring.repositorios.UsuarioRepository;
import com.therapp.spring.repositorios.TerapeutaRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepositorio;
    private final UsuarioPublicacionRepository usuarioPublicacionRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final TerapeutaRepository terapeutaRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepositorio, UsuarioPublicacionRepository usuarioPublicacionRepository, ConfirmationTokenRepository confirmationTokenRepository, PasswordEncoder passwordEncoder, EmailService emailService, TerapeutaRepository terapeutaRepository) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioPublicacionRepository = usuarioPublicacionRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.terapeutaRepository = terapeutaRepository;
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

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    //este metodo se encarga de guardar una lista de usuarios predefinidos en la base de datos
    public void saveAll(Iterable<Usuario> usuarios) {
        usuarios.forEach(usuario -> usuario.setClave(passwordEncoder.encode(usuario.getClave())));
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

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
    }

    public Optional<Usuario> findByUsername(String username) {
        Optional<Usuario> user = usuarioRepositorio.findByUsername(username);
        return user;
    }

    public Optional<Usuario> findByIdentifier(String identifier) {
        Optional<Usuario> user = usuarioRepositorio.findByEmail(identifier);
        if (user.isEmpty()) {
            user = usuarioRepositorio.findByUsername(identifier);
        }
        if (user.isEmpty()) {
            user = usuarioRepositorio.findByTelefono(identifier);
        }
        return user;
    }

    public Optional<Usuario> findByToken(String token) {
        return confirmationTokenRepository.findByToken(token).map(ConfirmationToken::getUsuario);
    }

    public void confirmUsuario(Usuario usuario) {
        usuario.setConfirmado(true);
        usuarioRepositorio.save(usuario);
    }

    public Usuario createUsuarioFromDTO(CreateUsuarioDTO createUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(createUsuarioDTO.getNombre());
        usuario.setUsername(createUsuarioDTO.getUsername());
        usuario.setEmail(createUsuarioDTO.getEmail());
        usuario.setConfirmado(false);
        usuario.setClave(passwordEncoder.encode(createUsuarioDTO.getClave()));
        usuario.setRol(Set.of(Rol.USER));
        usuario.setFechaNacimiento(createUsuarioDTO.getFechaNacimiento());
        usuario.setTelefono(createUsuarioDTO.getTelefono());
        usuario.setUbicacion(createUsuarioDTO.getUbicacion());
        usuario.setBiografia(null);
        return usuario;
    }

    public List<Usuario> buscarUsuarios(String query) {
        return usuarioRepositorio.findByNombreContainingIgnoreCaseOrUsernameContainingIgnoreCase(query, query);
    }

    public List<Usuario> obtenerUsuariosSeguidosSinConversacion(@RequestParam Long usuarioId) {
        return usuarioRepositorio.findUsuariosSeguidosSinConversacion(usuarioId);
    }

    public List<Usuario> obtenerUsuariosMasEnTherApp(@RequestParam Long usuarioId) {
        return usuarioRepositorio.findMasEnTherApp(usuarioId);
    }

    public boolean esTerapeuta(Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(usuarioId);
        if (usuario.isPresent()) {
            Optional<Terapeuta> terapeuta = terapeutaRepository.findByUsuario(usuario.get());
            return terapeuta.isPresent();
        }
        return false;
    }
}
