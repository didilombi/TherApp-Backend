package com.therapp.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.therapp.spring.modelo.Organizaciones;
import com.therapp.spring.servicios.OrganizacionesService;

@RestController
@RequestMapping("/api/organizaciones")
public class OrganizacionesController {

    private final OrganizacionesService organizacionesService;

    @Autowired
    public OrganizacionesController(OrganizacionesService organizacionesService) {
        this.organizacionesService = organizacionesService;
    }

    @GetMapping
    public List<Organizaciones> getAllOrganizaciones() {
        return organizacionesService.findAll();
    }

    @PostMapping
    public Organizaciones crearOrganizacion(@RequestBody Organizaciones organizacion) {
        return organizacionesService.save(organizacion);
    }

    @DeleteMapping("/{id}")
    public void borrarOrganizacion(@PathVariable Integer id) {
        organizacionesService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Organizaciones getOrganizacionById(@PathVariable Integer id) {
        return organizacionesService.findById(id);
    }
}
