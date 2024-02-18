package com.chatApi.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
        //create connection endpoint 		
		registry.addEndpoint("/ws").setAllowedOriginPatterns("http://localhost:5174").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		// Broker will route the msg to the destination starting with queue
		registry.enableSimpleBroker("/queue");
       //sender will send at the point of broker prefix with app  		
		registry.setApplicationDestinationPrefixes("/app");
	}

}
