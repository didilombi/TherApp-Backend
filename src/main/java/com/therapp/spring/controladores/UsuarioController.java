package com.therapp.spring.controladores;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Usuario;
import org.springframework.http.ResponseEntity;
import com.therapp.spring.servicios.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200") //esto es para permitir que angular se comunique con este controlador
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            // Asignar el rol por defecto si no viene en la petición
            if (usuario.getRol() == null) {
                usuario.setRol(Rol.USUARIO);
            }

            Usuario nuevoUsuario = usuarioService.save(usuario);
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
            // Llamamos al servicio para guardar la foto
            usuarioService.guardarFoto(id, foto);
            return ResponseEntity.ok("Foto subida correctamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al subir la foto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void borrarUsuario(@PathVariable Integer id) {
        usuarioService.deleteById(id);
    }
}
