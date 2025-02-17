package com.therapp.spring.controladores;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.therapp.spring.dto.CreateUsuarioDTO;
import com.therapp.spring.dto.PerfilDTO;
import com.therapp.spring.modelo.ConfirmationToken;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.EmailService;
import com.therapp.spring.servicios.SeguidorService;
import com.therapp.spring.servicios.UsuarioService;

import io.jsonwebtoken.lang.Collections;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200") //esto es para permitir que angular se comunique con este controlador
public class UsuarioController {

    

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final SeguidorService seguidorService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder, EmailService emailService, SeguidorService seguidorService) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.seguidorService = seguidorService;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody CreateUsuarioDTO createUsuarioDTO) {
        try {

            if (createUsuarioDTO.getRol() == null || createUsuarioDTO.getRol().isEmpty()) {
            createUsuarioDTO.setRol(Set.of(Rol.USER)); // Asigna un rol predeterminado
        }
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

    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> buscarUsuarios(@RequestParam String query) {
        List<Usuario> usuarios = usuarioService.buscarUsuarios(query);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/seguidores-comunes")
    public ResponseEntity<List<Usuario>> obtenerSeguidoresComunes(@RequestParam Long usuarioId, @RequestParam Long buscadoId) {
        List<Usuario> seguidoresComunes = seguidorService.obtenerSeguidoresComunes(usuarioId, buscadoId);
        return ResponseEntity.ok(seguidoresComunes);
    }

    @GetMapping("/seguidos-sin-conversacion")
    public ResponseEntity<List<Usuario>> obtenerUsuariosSeguidosSinConversacion(@RequestParam(name = "usuarioId") Long usuarioId) {
        if (usuarioId == null || usuarioId <= 0) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
        List<Usuario> usuarios = usuarioService.obtenerUsuariosSeguidosSinConversacion(usuarioId);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/mas-en-therapp")
    public ResponseEntity<List<Usuario>> obtenerUsuariosMasEnTherApp(@RequestParam Long usuarioId) {
        List<Usuario> usuarios = usuarioService.obtenerUsuariosMasEnTherApp(usuarioId);
        return ResponseEntity.ok(usuarios);
    }

}
