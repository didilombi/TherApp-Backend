package com.therapp.spring.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.therapp.spring.modelo.SolicitudTerapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.SolicitudTerapeutaRepository;

@Service
public class SolicitudTerapeutaService {

    @Autowired
    SolicitudTerapeutaRepository solicitudTerapeutaRepository;

    public List<SolicitudTerapeuta> findAll() {
        return solicitudTerapeutaRepository.findAll();
    }

    public SolicitudTerapeuta findByUsuario(Usuario u) {
        return solicitudTerapeutaRepository.findByUsuario(u);
    }

    public void save(SolicitudTerapeuta solicitud) {
        solicitudTerapeutaRepository.save(solicitud);
    }

    public SolicitudTerapeuta findByEmail(String email) {
        return solicitudTerapeutaRepository.findByEmail(email);
    }

    public void delete(SolicitudTerapeuta solicitud) {
        solicitudTerapeutaRepository.delete(solicitud);
    }
}
