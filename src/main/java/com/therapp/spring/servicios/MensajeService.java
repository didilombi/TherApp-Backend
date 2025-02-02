package com.therapp.spring.servicios;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.MensajeRepository;
import com.therapp.spring.repositorios.TerapeutaRepository;
import com.therapp.spring.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TerapeutaRepository terapeutaRepository;

    // Obtener mensajes entre un usuario y un terapeuta
    public List<Mensaje> obtenerMensajes(Integer usuarioId, Integer terapeutaId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Terapeuta> terapeuta = terapeutaRepository.findById(terapeutaId);

        if (usuario.isPresent() && terapeuta.isPresent()) {
            return mensajeRepository.findByUsuarioAndTerapeuta(usuario.get(), terapeuta.get());
        }
        return null; // Si no se encuentran los usuarios, devuelve null
    }


    public Mensaje enviarMensaje(Integer usuarioId, Integer terapeutaId, String contenido) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        Optional<Terapeuta> terapeuta = terapeutaRepository.findById(terapeutaId);
    
        if (usuario.isPresent() && terapeuta.isPresent()) {
            Mensaje mensaje = new Mensaje();
            mensaje.setContenido(contenido);  // asegurarse de que se guarda como texto
            mensaje.setFechaEnvio(new Date());
            mensaje.setVisto(false);
            mensaje.setUsuario(usuario.get());
            mensaje.setTerapeuta(terapeuta.get());
    
            return mensajeRepository.save(mensaje);  // guarda el mensaje en la base de datos
        }
    
        return null;
    }
    

}
