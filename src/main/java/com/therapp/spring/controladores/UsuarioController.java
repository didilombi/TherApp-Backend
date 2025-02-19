package com.therapp.spring.controladores;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.therapp.spring.dto.ConversacionDTO;
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

    @GetMapping("/conversaciones/{id}")
    public ConversacionDTO getConversaciones(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id).get();
        ConversacionDTO conversacionDTO = new ConversacionDTO(usuario);
        return conversacionDTO;
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

    @PostMapping("/haceradmin")
    public void hacerAdmin(@RequestBody Map<String, String> email){
        System.out.println(email);
        Optional<Usuario> u = usuarioService.findByEmail(email.get("email"));
        
        u.ifPresent(usuario -> {
            if(usuario.getRol().contains(Rol.USER)){
                usuario.cambiarRol(Rol.ADMIN);
            }
        });
        u.ifPresent(usuario -> usuarioService.save(usuario));
    }

    

    @PostMapping("/{nombreUsuario}/foto")
    public ResponseEntity<?> subirFotoPerfil(@PathVariable String nombreUsuario, @RequestParam("foto") MultipartFile foto) {
        Usuario usuario = usuarioService.findByUsername(nombreUsuario).orElse(null);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        // Aquí puedes guardar el archivo en el servidor y obtener la URL del archivo
        String fotoUrl = guardarArchivo(foto);

        usuario.setFotoPerfil(fotoUrl);
        usuarioService.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    private static final String UPLOAD_DIR = "uploads/";

    private String guardarArchivo(MultipartFile archivo) {
        // Crear el directorio de subida si no existe
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Obtener el nombre original del archivo
        String originalFilename = archivo.getOriginalFilename();
        if (originalFilename == null) {
            throw new RuntimeException("El archivo no tiene nombre");
        }

        // Crear la ruta completa del archivo
        Path filePath = Paths.get(UPLOAD_DIR, originalFilename);

        try {
            // Guardar el archivo en el servidor
            Files.copy(archivo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo", e);
        }

        // Devolver la URL del archivo
        return originalFilename;
    }
}
