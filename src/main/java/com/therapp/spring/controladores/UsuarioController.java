package com.therapp.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    // @PutMapping("/{id}")
    // public Usuario modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails) {
    //     return usuarioService.findById(id)
    //             .map(usuario -> {
    //                 usuario.setNombre(usuarioDetails.getNombre());
    //                 usuario.setEmail(usuarioDetails.getEmail());
    //                 return usuarioService.save(usuario);
    //             })
    //             .orElse(null);
    // }

    // @DeleteMapping("/{id}")
    // public void borrarUsuario(@PathVariable Long id) {
    //     usuarioService.deleteById(id);
    // }
}
