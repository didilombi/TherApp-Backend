package com.therapp.spring.controladores;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.UsuarioService;
=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.therapp.spring.dto.UsuarioDTO;
import com.therapp.spring.dto.TerapeutaDTo;
import com.therapp.spring.dto.ConverserDTO;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.servicios.UsuarioService;
import com.therapp.spring.servicios.TerapeutaService;
>>>>>>> a77ffc3 (implementacion de los DTO de terapeuta y usuario)

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TerapeutaService terapeutaService;

    public UsuarioController(UsuarioService usuarioService, TerapeutaService terapeutaService) {
        this.usuarioService = usuarioService;
        this.terapeutaService = terapeutaService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getRol() == Rol.TERAPEUTA) { // ✅ Comparación correcta con enum
            TerapeutaDTo terapeutaDTO = ConverserDTO.convertUsuarioToTerapeutaDTO(usuarioDTO);
            return ResponseEntity.ok(terapeutaService.registrarTerapeuta(terapeutaDTO));
        } else {
            return ResponseEntity.ok(usuarioService.registrarUsuario(usuarioDTO));
        }
    }
<<<<<<< HEAD

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void borrarUsuario(@PathVariable Integer id) {
        usuarioService.deleteById(id);
    }
=======
>>>>>>> a77ffc3 (implementacion de los DTO de terapeuta y usuario)
}
