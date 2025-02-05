package com.therapp.spring.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");  // 📩 Canal de suscripción para clientes
        registry.setApplicationDestinationPrefixes("/app"); // 📤 Prefijo para enviar mensajes desde el frontend
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")  // 📡 WebSocket en "/ws"
                .setAllowedOrigins("*") // Permitir cualquier frontend
                .withSockJS(); // Soporte para navegadores sin WebSocket nativo
    }
}
