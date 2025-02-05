package com.therapp.spring.controladores;

import com.therapp.spring.dto.MensajeDTO;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.servicios.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class ChatWebSocketController {

    @Autowired
    private MensajeService mensajeService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;  // 🔥 Enviar mensajes en tiempo real

    // 📥 Cliente envía mensaje a "/app/chat"
    @MessageMapping("/chat")
    public void enviarMensaje(MensajeDTO mensaje) {
        mensaje.setFechaEnvio(new Date());

        // Guardar el mensaje en la base de datos
        Mensaje nuevoMensaje = mensajeService.enviarMensaje(
            mensaje.getEmisorId(),
            mensaje.getReceptorId(),
            mensaje.getContenido(),
            mensaje.getArchivoUrl()
        );

        // 🔥 Enviar mensaje a los suscriptores en "/topic/chat/{receptorId}"
        MensajeDTO mensajeDTO = new MensajeDTO(
            nuevoMensaje.getId(),
            nuevoMensaje.getContenido(),
            nuevoMensaje.getFechaEnvio(),
            nuevoMensaje.getVisto(),
            nuevoMensaje.getEmisor().getId(),
            nuevoMensaje.getEmisor().getNombre(),
            nuevoMensaje.getReceptor().getId(),
            nuevoMensaje.getReceptor().getNombre(),
            nuevoMensaje.getArchivoUrl()
        );

        messagingTemplate.convertAndSend("/topic/chat/" + mensaje.getReceptorId(), mensajeDTO);
    }
}
