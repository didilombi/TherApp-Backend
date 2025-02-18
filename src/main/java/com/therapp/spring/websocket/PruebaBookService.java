package com.therapp.spring.websocket;

import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.Mensaje;


//cuando websocket funcione, borrar esta clase

@Service
public class PruebaBookService {

    private PruebaMensajeService pruebaMensajeService;

    public void testWebSocket(String userId) {
        pruebaMensajeService.sendMensaje(
            userId, 
            Mensaje.builder()
                .contenido("Hola, soy un mensaje de prueba")
                .build()
        );
    }


}
