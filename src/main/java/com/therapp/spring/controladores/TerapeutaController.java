package com.therapp.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.servicios.TerapeutaService;

@RestController
@RequestMapping("/api/terapeuta")
public class TerapeutaController {
    @Autowired
    private TerapeutaService terapeutaService;


    //endpoints para listar y registrar terapeutas
    @GetMapping
    public List<Terapeuta> getAllTerapeutas() {
        return terapeutaService.findAll();
    }

    @PostMapping
    public Terapeuta añadiTerapeuta(@RequestBody Terapeuta terapeuta) {
        return terapeutaService.save(terapeuta);
    }
}
