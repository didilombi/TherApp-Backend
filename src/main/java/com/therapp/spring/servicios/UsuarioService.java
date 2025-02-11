package com.therapp.spring.servicios;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.ConfirmationTokenRepository;
import com.therapp.spring.repositorios.UsuarioPublicacionRepository;
import com.therapp.spring.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepositorio;
   

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepositorio, UsuarioPublicacionRepository usuarioPublicacionRepository, ConfirmationTokenRepository confirmationTokenRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioPublicacionRepository = usuarioPublicacionRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public List<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepositorio.findById(id);
    }

    public Usuario save(Usuario usuario) {
        //Si el usuario NO trae foto, le ponemos la ruta por defecto
        if (usuario.getFotoPerfil() == null || usuario.getFotoPerfil().isEmpty()) {
            usuario.setFotoPerfil("/imagenes/Perfil-inicial.jpg");
        }
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

    // public void delete(Usuario u) {
    //     terapeutaService.deleteByUsuario(u);
    //     usuarioRepositorio.delete(u);
    // }

     // Endpoint para cambiar foto:
    public void guardarFoto(Integer id, MultipartFile file) throws Exception {
        Usuario usuario = usuarioRepositorio.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));

        if (!Files.exists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }

        // Generamos un nombre único para la foto
        String filename = "usuario_" + id + "_" + file.getOriginalFilename();
        Path destinationFile = rootLocation.resolve(filename).normalize();

        // Copiamos el contenido del archivo subido
        Files.copy(
            file.getInputStream(),
            destinationFile,
            StandardCopyOption.REPLACE_EXISTING
        );

        // Guardamos la ruta en la DB (ej. "uploads/usuario_5_foto.jpg")
        usuario.setFotoPerfil(destinationFile.toString());
        usuarioRepositorio.save(usuario);
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
        usuario.setClave(passwordEncoder.encode(createUsuarioDTO.getClave()));
        usuario.setRol(Set.of(Rol.USER));
        usuario.setFechaNacimiento(createUsuarioDTO.getFechaNacimiento());
        usuario.setTelefono(createUsuarioDTO.getTelefono());
        usuario.setUbicacion(createUsuarioDTO.getUbicacion());
        usuario.setBiografia(null);
        return usuario;
    }

}
