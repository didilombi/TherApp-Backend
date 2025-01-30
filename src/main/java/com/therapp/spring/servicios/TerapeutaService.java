package com.therapp.spring.servicios;

import org.springframework.stereotype.Service;
import java.util.List;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
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

    public Terapeuta save(Terapeuta terapeuta) {
        return terapeutaRepositorio.save(terapeuta);
    }

    public void saveAll(Iterable<Terapeuta> terapeutas) {
        terapeutaRepositorio.saveAll(terapeutas);
    }

    public void deleteById(Integer id) {
        terapeutaRepositorio.deleteById(id);
    }

    public void deleteByUsuario(Usuario usuario) {
        Terapeuta terapeuta = terapeutaRepositorio.findByUsuario(usuario);
        if (terapeuta != null) {
            terapeutaRepositorio.delete(terapeuta);
        }
    }
}
