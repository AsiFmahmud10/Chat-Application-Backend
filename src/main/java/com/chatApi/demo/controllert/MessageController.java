package com.chatApi.demo.controllert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatApi.demo.dto.LoginByEmailDto;
import com.chatApi.demo.dto.SendMessageDto;
import com.chatApi.demo.dto.SpecificMsgDto;
import com.chatApi.demo.entity.Message;
import com.chatApi.demo.entity.User;
import com.chatApi.demo.service.MessageService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:5174",allowCredentials = "true")
@RestController
public class MessageController {
    
	@Autowired
	MessageService messageService; 
	
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate ; 
	
	@GetMapping("/message")
	public List<Message> get(HttpSession session ) {
		User user = (User) session.getAttribute("id");
		
		return messageService.get();
	}

	
	
	@PostMapping("/message")
	public String sendMessage(@RequestBody SendMessageDto msgDto, HttpSession session ) {
		User user = (User) session.getAttribute("id");
		Message message = new Message();
		message.setSenderId(user.getId());
		message.setReceiverId(msgDto.getReceiverId());
		message.setContent(msgDto.getMsg());
		messageService.save(message);
		
		return "err?" ;
	}
	
	@PostMapping("/message/reciever")
	public List<Message> findMessagesOfSenderAndReciever(@RequestBody SpecificMsgDto msgDto, HttpSession session) {
		User user = (User) session.getAttribute("id");
				
		return messageService.findMessagesOfSenderAndReciever(user.getId(), msgDto.getReceiverId());
		
	}
	
	@MessageMapping("/user-message-{userId}")
	public void sendTouser(@Payload String message, @DestinationVariable String userId ) {
		simpMessagingTemplate.convertAndSend("/queue/reply-"+ userId,message);
	}
	
}
