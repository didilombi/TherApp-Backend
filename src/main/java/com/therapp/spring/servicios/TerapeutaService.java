package com.therapp.spring.servicios;

import org.springframework.stereotype.Service;
import java.util.List;

import com.therapp.spring.dto.TerapeutaDTo;
import com.therapp.spring.modelo.Terapeuta;
import java.util.Optional;
import com.therapp.spring.repositorios.TerapeutaRepository;

@Service
public class TerapeutaService {

    private final TerapeutaRepository terapeutaRepositorio;

    public TerapeutaService(TerapeutaRepository terapeutaRepositorio) {
        this.terapeutaRepositorio = terapeutaRepositorio;
    }

    public List<Terapeuta> findAll() {
        return terapeutaRepositorio.findAll();
    }

    public Optional<Terapeuta> findById(Integer id) {
        return terapeutaRepositorio.findById(id);
    }
    

    public Terapeuta save(Terapeuta terapeuta) {
        return terapeutaRepositorio.save(terapeuta);
    }

    public void saveAll(Iterable<Terapeuta> terapeutas) {
        terapeutaRepositorio.saveAll(terapeutas);
    }

    public void deleteById(Long id) {
        terapeutaRepositorio.deleteById(id.intValue());
    }

    public Terapeuta registrarTerapeuta(TerapeutaDTo TerapeutaDTO) {
        Terapeuta terapeuta = new Terapeuta();
        terapeuta.setNombre(TerapeutaDTO.getNombre());
        terapeuta.setEspecialidad(TerapeutaDTO.getEspecialidad());
        terapeuta.setNombreUsuario(TerapeutaDTO.getNombreUsuario());
        terapeuta.setEmail(TerapeutaDTO.getEmail());
        return terapeutaRepositorio.save(terapeuta);
    }
    
}
