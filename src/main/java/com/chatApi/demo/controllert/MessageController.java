package com.chatApi.demo.controllert;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
@RestController
public class MessageController {

	@Autowired
	MessageService messageService;

	@PostMapping("/message")
	public ResponseEntity<Message> sendMessage(@RequestBody SendMessageDto msgDto, HttpSession session) {

		try {
			User user = (User) session.getAttribute("id");

			// create new message and save
			Message message = new Message();
			message.setSenderId(user.getId());
			message.setReceiverId(msgDto.getReceiverId());
			message.setContent(msgDto.getMsg());

			return new ResponseEntity<Message>(messageService.save(message), HttpStatus.CREATED);

		} catch (IllegalStateException e) {
			return null;
		}

	}

	@DeleteMapping("/message/{messageId}")
	public ResponseEntity<String> remove(@PathVariable long messageId, HttpSession session) {
		
		try {
			User user = (User) session.getAttribute("id");
			messageService.delete(messageId);

		} catch (IllegalStateException e) {
			return null;
		}

		return ResponseEntity.ok("Message delete Successful");
	}

	@PostMapping("/message/reciever")
	public ResponseEntity<List<Message>> findMessagesOfSenderAndReciever(@RequestBody SpecificMsgDto msgDto,
			HttpSession session) {
		
		try {
			User user = (User) session.getAttribute("id");
			return new ResponseEntity<List<Message>>(
					messageService.findMessagesOfSenderAndReciever(user.getId(), msgDto.getReceiverId()),
					HttpStatus.OK);

		} catch (IllegalStateException e) {
			return null;
		}

	}

	@MessageMapping("/notify-{userId}")
	@SendTo("/topic/sub")
	public String notify(@Payload String signal, @DestinationVariable String userId) {
		// send message to the users who subscribe to "/topic/sub"

		return userId;
	}

//	@GetMapping("/message")
//	public List<Message> get(HttpSession session ) {
//		
//		User user = (User) session.getAttribute("id");
//		
//		return messageService.get();
//	}

}
