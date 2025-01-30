package com.therapp.spring.controladores;

import org.springframework.web.bind.annotation.*;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.servicios.TerapeutaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import com.therapp.spring.dto.TerapeutaDTo;

@CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde Angular
@RestController
@RequestMapping("/api/terapeutas")
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

    @PostMapping
    public Terapeuta addTerapeuta(@RequestBody TerapeutaDTo terapeutaDTO) {
        Terapeuta terapeuta = new Terapeuta();
        terapeuta.setNombre(terapeutaDTO.getNombre());
        terapeuta.setEspecialidad(terapeutaDTO.getEspecialidad());
        terapeuta.setNombreUsuario(terapeutaDTO.getNombreUsuario());
        terapeuta.setEmail(terapeutaDTO.getEmail());

        return terapeutaService.save(terapeuta);
    }

    @PutMapping("/{id}")
    public Terapeuta updateTerapeuta(@PathVariable Integer id, @RequestBody Terapeuta terapeutaDetails) {
        Optional<Terapeuta> terapeutaList = terapeutaService.findById(id);
        if (terapeutaList.isEmpty()) {
            throw new RuntimeException("Terapeuta no encontrado");
        }

        Terapeuta terapeuta = terapeutaList.get();
        terapeuta.setNombre(terapeutaDetails.getNombre());
        terapeuta.setEspecialidad(terapeutaDetails.getEspecialidad());
        terapeuta.setEmail(terapeutaDetails.getEmail());
        
        return terapeutaService.save(terapeuta);
    }


    @DeleteMapping("/{id}")
    public void deleteTerapeuta(@PathVariable Long id) {
        terapeutaService.deleteById(id);
    }
}
