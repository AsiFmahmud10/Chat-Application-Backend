package com.chatApi.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApi.demo.entity.Message;

import com.chatApi.demo.entity.User;
import com.chatApi.demo.repository.MessageRepository;


@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	
	
	public List<Message> get( ) {
		
		return messageRepository.findAll();
	}
	
	public Message save(Message message) {
		return messageRepository.save(message);
	}
	
	public void delete(long messageId) {
		 messageRepository.deleteById(messageId);
	}
	
	public List<Message> findMessagesOfSenderAndReciever(long senderId, long receieverId) {
		
		return messageRepository.findMessages(senderId, receieverId);
	}
}
