package com.chatApi.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chatApi.demo.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {
	
	@Query( 
		value = "select * from Message where (sender_Id = :senderId and receiver_Id = :receieverId) or (sender_Id = :receieverId and receiver_Id = :senderId)",
		nativeQuery = true 
	)
	List <Message> findMessages( long senderId, long receieverId);
}
