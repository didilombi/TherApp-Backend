package com.therapp.spring.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.therapp.spring.repositorios.TerapeutaRepository;
import com.therapp.spring.modelo.Terapeuta;
import java.util.List;

@Service
public class TerapeutaService {
    //aqui se implementan los metodos que se van a usar en el controlador

    @Autowired
    private TerapeutaRepository terapeutaRepositorio;

    // Implementacion  para poder visualizar y guardar terapeutas
    public List<Terapeuta> findAll() {
        return terapeutaRepositorio.findAll();
    }

    public Terapeuta save(Terapeuta terapeuta) {
        return terapeutaRepositorio.save(terapeuta);
    }
}
