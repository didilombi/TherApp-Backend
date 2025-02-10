package com.therapp.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.modelo.Organizacion;
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
    public List<Organizacion> getAllOrganizaciones() {
        return organizacionesService.findAll();
    }

    @PostMapping
    public Organizacion crearOrganizacion(@RequestBody Organizacion organizacion) {
        return organizacionesService.save(organizacion);
    }

    @DeleteMapping("/{id}")
    public void borrarOrganizacion(@PathVariable Long id) {
        organizacionesService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Organizacion getOrganizacionById(@PathVariable Long id) {
        return organizacionesService.findById(id);
    }
}
