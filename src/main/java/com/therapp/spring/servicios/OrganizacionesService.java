package com.therapp.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.Organizaciones;
import com.therapp.spring.repositorios.OrganizacionesRepository;

@Service
public class OrganizacionesService {

    @Autowired
    private OrganizacionesRepository organizacionesRepositorio;

    public List<Organizaciones> findAll() {
        return organizacionesRepositorio.findAll();
    }

    public Organizaciones save(Organizaciones organizacion) {
        return organizacionesRepositorio.save(organizacion);
    }

    public void deleteById(Integer id) {
        organizacionesRepositorio.deleteById(id);
    }

    public Organizaciones findById(Integer id) {
        return organizacionesRepositorio.findById(id).orElse(null);
    }
}
