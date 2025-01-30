package com.therapp.spring.servicios;

import org.springframework.stereotype.Service;
import java.util.List;

import com.therapp.spring.dto.TerapeutaDTo;
import com.therapp.spring.modelo.Terapeuta;
<<<<<<< HEAD
import com.therapp.spring.modelo.Usuario;
=======
import java.util.Optional;
>>>>>>> a77ffc3 (implementacion de los DTO de terapeuta y usuario)
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

<<<<<<< HEAD
    public void deleteById(Integer id) {
        terapeutaRepositorio.deleteById(id);
    }

    public void deleteByUsuario(Usuario usuario) {
        Terapeuta terapeuta = terapeutaRepositorio.findByUsuario(usuario);
        if (terapeuta != null) {
            terapeutaRepositorio.delete(terapeuta);
        }
    }
=======
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
    
>>>>>>> a77ffc3 (implementacion de los DTO de terapeuta y usuario)
}
