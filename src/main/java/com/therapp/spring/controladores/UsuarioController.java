package com.therapp.spring.controladores;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Usuario;

import org.springframework.http.ResponseEntity;

import com.therapp.spring.dto.UsuarioDTO;
import com.therapp.spring.servicios.UsuarioService;
import com.therapp.spring.servicios.TerapeutaService;

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
                Set<Rol> roles = new HashSet<>();
                roles.add(Rol.USUARIO);
                usuario.setRol(roles);
            }

            Usuario nuevoUsuario = usuarioService.save(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public void borrarUsuario(@PathVariable Long id) {
        usuarioService.deleteById(id);
    }
}
