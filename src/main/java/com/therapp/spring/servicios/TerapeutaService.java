package com.therapp.spring.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.TerapeutaRepository;

@Service
public class TerapeutaService {

    private final TerapeutaRepository terapeutaRepositorio;

    @Autowired
    public TerapeutaService(TerapeutaRepository terapeutaRepositorio) {
        this.terapeutaRepositorio = terapeutaRepositorio;
    }

    public List<Terapeuta> findAll() {
        return terapeutaRepositorio.findAll();
    }

    public Optional<Terapeuta> findById(Long id) {
        return terapeutaRepositorio.findById(id);
    }
    

    public Terapeuta save(Terapeuta terapeuta) {
        return terapeutaRepositorio.save(terapeuta);
    }

    public void saveAll(Iterable<Terapeuta> terapeutas) {
        terapeutaRepositorio.saveAll(terapeutas);
    }

    public void deleteById(Long id) {
        terapeutaRepositorio.deleteById(id.longValue());
    }

    public Optional<Terapeuta> findByUsuario(Usuario u){
        return terapeutaRepositorio.findByUsuario(u);
    }

    // public Terapeuta registrarTerapeuta(TerapeutaDTo TerapeutaDTO) {
    //     Terapeuta terapeuta = new Terapeuta();
    //     terapeuta.setNombre(TerapeutaDTO.getNombre());
    //     terapeuta.setEspecialidad(TerapeutaDTO.getEspecialidad());
    //     terapeuta.setUsername(TerapeutaDTO.getUsername());
    //     terapeuta.setEmail(TerapeutaDTO.getEmail());
    //     return terapeutaRepositorio.save(terapeuta);
    // }
    
}
