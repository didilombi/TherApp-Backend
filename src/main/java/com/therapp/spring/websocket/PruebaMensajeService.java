package com.therapp.spring.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.Mensaje;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PruebaMensajeService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendMensaje(String userId, Mensaje mensaje) {
        log.info("Enviando mensaje {} a usuario {}", mensaje, userId);
        messagingTemplate.convertAndSendToUser(
            userId, 
            "/notification", 
            mensaje
        );

    }

}
