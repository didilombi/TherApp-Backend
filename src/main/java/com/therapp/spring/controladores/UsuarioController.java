package com.therapp.spring.controladores;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.therapp.spring.dto.CreateUsuarioDTO;
import com.therapp.spring.dto.PerfilDTO;
import com.therapp.spring.modelo.ConfirmationToken;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.EmailService;
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
    private final EmailService emailService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody CreateUsuarioDTO createUsuarioDTO) {
        try {
            Usuario usuario = usuarioService.createUsuarioFromDTO(createUsuarioDTO);

            Usuario nuevoUsuario = usuarioService.save(usuario);

            // Generar token de confirmación
            ConfirmationToken confirmationToken = new ConfirmationToken(nuevoUsuario);
            usuarioService.saveConfirmationToken(confirmationToken);

            // Enviar correo de confirmación
            String subject = "Confirmación de Registro";
            String text = "Hola " + usuario.getNombre() + ",\n\nGracias por registrarte en nuestra aplicación. Por favor, confirma tu correo electrónico haciendo clic en el siguiente enlace:\n"
                    + "http://localhost:9000/api/usuarios/confirmar?token=" + confirmationToken.getToken();
            emailService.sendEmail(usuario.getEmail(), subject, text);

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
    @GetMapping("/confirmar")
    public ResponseEntity<?> confirmarUsuario(@RequestParam String token) {
        Optional<Usuario> usuarioOpt = usuarioService.findByToken(token);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuarioService.confirmUsuario(usuario);
            return ResponseEntity.ok("Correo electrónico confirmado exitosamente.");
        } else {
            return ResponseEntity.badRequest().body("Token de confirmación inválido.");
        }
    }

    @DeleteMapping("/{id}")
    public void borrarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}
