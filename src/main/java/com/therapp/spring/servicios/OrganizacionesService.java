package com.therapp.spring.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.therapp.spring.modelo.Organizacion;
import com.therapp.spring.repositorios.OrganizacionesRepository;

@Service
public class OrganizacionesService {

    @Autowired
    private OrganizacionesRepository organizacionesRepositorio;

    public List<Organizacion> findAll() {
        return organizacionesRepositorio.findAll();
    }

    public Organizacion save(Organizacion organizacion) {
        return organizacionesRepositorio.save(organizacion);
    }

    public void deleteById(Long id) {
        organizacionesRepositorio.deleteById(id);
    }

    public Organizacion findById(Long id) {
        return organizacionesRepositorio.findById(id).orElse(null);
    }
}
