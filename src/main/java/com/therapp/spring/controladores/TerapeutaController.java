package com.therapp.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.therapp.spring.dto.TerapeutaDTO;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.TerapeutaService;
import com.therapp.spring.servicios.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde Angular
@RestController
@RequestMapping("/api/terapeutas")
@Tag(name = "Terapeutas") // Anotación para detectar el tag de terapeutas
public class TerapeutaController {

    private final TerapeutaService terapeutaService;
    private final UsuarioService usuarioService;

    @Autowired
    public TerapeutaController(TerapeutaService terapeutaService, UsuarioService usuarioService) {
        this.terapeutaService = terapeutaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Terapeuta> getAllTerapeutas() {
        return terapeutaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Terapeuta> getTerapeutaById(@PathVariable Integer id) {
        return terapeutaService.findById(id);
    }
    @PostMapping
    public Terapeuta addTerapeuta(@RequestBody Terapeuta terapeuta) {
        Usuario usuario = usuarioService.findById(terapeuta.getUsuario().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        terapeuta.setUsuario(usuario);
        return terapeutaService.save(terapeuta);
    }
    @DeleteMapping("/{id}")
    public void borrarTerapeuta(@PathVariable Integer id) {
        terapeutaService.deleteById(id);
    }
}
