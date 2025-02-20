package com.therapp.spring.servicios;

import com.therapp.spring.dto.MensajeDTO;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.MensajeRepository;
import com.therapp.spring.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Mensaje save(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    // Obtener todos los mensajes entre dos usuarios (bidireccional)
    public List<MensajeDTO> obtenerChat(Long userId1, Long userId2, boolean ponerMensajesEnVisto) {
    List<Mensaje> mensajes = mensajeRepository.findChatBetweenUsers(userId1, userId2);
    
    if (ponerMensajesEnVisto) {   
        mensajes.stream().map(m -> {
            Mensaje mensaje = mensajeRepository.findById(m.getId()).orElse(null);
            if (mensaje != null && mensaje.getReceptor().getId() == userId1) {
                mensaje.setVisto(true);
            }
            return mensaje;
        }).toList();
        
        mensajeRepository.saveAll(mensajes);
    }

        return mensajes.stream().map(m -> new MensajeDTO(
            m.getId(),
            m.getContenido(),
            m.getFechaEnvio(),
            m.getVisto(),
            m.getEmisor().getId(),
            m.getEmisor().getNombre(),
            m.getReceptor().getId(),
            m.getReceptor().getNombre(),
            m.getArchivoUrl()
        )).collect(Collectors.toList());
    }
    

    // Enviar un nuevo mensaje de userId1 -> userId2
    public Mensaje enviarMensaje(Long emisorId, Long receptorId, String contenido, String archivoUrl) {
        Optional<Usuario> emisorOpt = usuarioRepository.findById(emisorId);
        Optional<Usuario> receptorOpt = usuarioRepository.findById(receptorId);

        if (emisorOpt.isPresent() && receptorOpt.isPresent()) {
            Mensaje mensaje = new Mensaje();
            mensaje.setContenido(contenido);
            mensaje.setFechaEnvio(new Date());
            mensaje.setVisto(false);
            mensaje.setEmisor(emisorOpt.get());
            mensaje.setReceptor(receptorOpt.get());
            mensaje.setArchivoUrl(archivoUrl); // ✅ Ahora el mensaje puede incluir archivos

            return mensajeRepository.save(mensaje);
        }

        return null;
    }

    public List<Mensaje> obtenerMensajes(Long usuarioId, Long receptorId) {
        if (usuarioId == null || receptorId == null || usuarioId <= 0 || receptorId <= 0) {
            throw new IllegalArgumentException("IDs de usuario no válidos");
        }
        return mensajeRepository.findChatBetweenUsers(usuarioId, receptorId);
    }
    
    public List<Usuario> obtenerConversaciones(Long usuarioId) {
        List<Long> idsConversaciones = mensajeRepository.findConversacionesByUsuarioId(usuarioId);
        return usuarioRepository.findAllById(idsConversaciones);
    } 
}