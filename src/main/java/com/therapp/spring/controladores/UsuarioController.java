package com.therapp.spring.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.therapp.spring.dto.UsuarioDTO;
import com.therapp.spring.dto.TerapeutaDTo;
import com.therapp.spring.dto.ConverserDTO;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.servicios.UsuarioService;
import com.therapp.spring.servicios.TerapeutaService;

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
}
