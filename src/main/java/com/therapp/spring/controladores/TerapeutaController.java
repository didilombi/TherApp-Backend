package com.therapp.spring.controladores;

import org.springframework.web.bind.annotation.*;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.servicios.TerapeutaService;

import java.util.List;

@RestController
@RequestMapping("/api/terapeutas")
public class TerapeutaController {

    private final TerapeutaService terapeutaService;

    public TerapeutaController(TerapeutaService terapeutaService) {
        this.terapeutaService = terapeutaService;
    }

    @GetMapping
    public List<Terapeuta> getAllTerapeutas() {
        return terapeutaService.findAll();
    }

    @PostMapping
    public Terapeuta addTerapeuta(@RequestBody Terapeuta terapeuta) {
        return terapeutaService.save(terapeuta);
    }

    @DeleteMapping("/{id}")
    public void borrarTerapeuta(@PathVariable Integer id) {
        terapeutaService.deleteById(id);
    }
}
