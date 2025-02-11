package com.therapp.spring.controladores;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import org.springframework.http.MediaType;
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
            // Si no traen rol, le ponemos el rol USUARIO
            if (usuario.getRol() == null) {
                usuario.setRol(Rol.USUARIO);
            }
            // 🔥 Aquí no forzamos foto, se hará en usuarioService.save()
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

    @PostMapping(value = "/{id}/foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> subirFoto(
        @PathVariable Integer id,
        @RequestParam("foto") MultipartFile foto
    ) {
        try {
            usuarioService.guardarFoto(id, foto);
            return ResponseEntity.ok("Foto subida correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al subir la foto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void borrarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}
