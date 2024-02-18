package com.chatApi.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatApi.demo.entity.User;
import com.chatApi.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public List<User> getAll() {
		
		return userRepository.findAll();
	}
	
	public User get(User user) {
		User newUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException());
        
		return newUser;
	}

	public User save(User user) {
		return userRepository.save(user);
	}
	
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
