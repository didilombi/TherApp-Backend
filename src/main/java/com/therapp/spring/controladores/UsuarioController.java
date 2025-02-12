package com.therapp.spring.controladores;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.therapp.spring.dto.CreateUsuarioDTO;
import com.therapp.spring.dto.PerfilDTO;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200") //esto es para permitir que angular se comunique con este controlador
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/registro")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody CreateUsuarioDTO createUsuarioDTO) {
        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(createUsuarioDTO.getNombre());
            usuario.setUsername(createUsuarioDTO.getUsername());
            usuario.setEmail(createUsuarioDTO.getEmail());
            usuario.setClave(createUsuarioDTO.getClave());
            usuario.setRol(createUsuarioDTO.getRol() != null ? createUsuarioDTO.getRol() : Set.of(Rol.USER));
            usuario.setFechaNacimiento(createUsuarioDTO.getFechaNacimiento());
            usuario.setTelefono(createUsuarioDTO.getTelefono());
            usuario.setUbicacion(createUsuarioDTO.getUbicacion());
            usuario.setBiografia(null);

            Usuario nuevoUsuario = usuarioService.save(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{nombre}")
    public PerfilDTO getPerfilDTO(@PathVariable String nombre) {
        PerfilDTO perfilDTO = new PerfilDTO(usuarioService.findByUsername(nombre));
        return perfilDTO;
    }    

    @DeleteMapping("/{id}")
    public void borrarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}
