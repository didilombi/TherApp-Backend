package com.therapp.spring.controladores;

import org.springframework.web.bind.annotation.*;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.servicios.TerapeutaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde Angular
@RestController
@RequestMapping("/api/terapeutas")
@Tag(name = "Terapeutas") //anotacion para detectar el tag de terapeutas
@Tag(name = "Terapeutas") //anotacion para detectar el tag de terapeutas
public class TerapeutaController {

    private final TerapeutaService terapeutaService;
    
    

    public TerapeutaController(TerapeutaService terapeutaService) {
        this.terapeutaService = terapeutaService;
    }

    @GetMapping
    public List<Terapeuta> getAllTerapeutas() {
        return terapeutaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Terapeuta> getTerapeutaById(@PathVariable Integer id) {
        return terapeutaService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void borrarTerapeuta(@PathVariable Integer id) {
        terapeutaService.deleteById(id);
    }
}
