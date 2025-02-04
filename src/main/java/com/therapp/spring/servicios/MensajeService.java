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
    public List<MensajeDTO> obtenerChat(Integer userId1, Integer userId2) {
    List<Mensaje> mensajes = mensajeRepository.findChatBetweenUsers(userId1, userId2);
        
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
    public Mensaje enviarMensaje(Integer emisorId, Integer receptorId, String contenido, String archivoUrl) {
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
}
