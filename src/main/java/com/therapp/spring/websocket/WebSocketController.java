package com.therapp.spring.websocket;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import com.therapp.spring.dto.MensajeDTO;

@Controller
public class WebSocketController {
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public MensajeDTO chat(@DestinationVariable String roomId, MensajeDTO mensaje) {
        System.out.println(mensaje);
        return mensaje;
    }
}
