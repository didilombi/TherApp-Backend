package com.therapp.spring.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketInterceptor webSocketInterceptor;

    public WebsocketConfig(WebSocketInterceptor webSocketInterceptor) {
        this.webSocketInterceptor = webSocketInterceptor;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-socket")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS()
                .setInterceptors(webSocketInterceptor);  // Add interceptor
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // Enables a simple in-memory message broker
        registry.setApplicationDestinationPrefixes("/app"); // Prefix for sending messages
    }
}
