package com.chatApi.demo.response;

import java.util.List;

import com.chatApi.demo.entity.User;

public class UserAndContactResponse {
	
	private User user;
	private List<User> contacts;
	
	
	public UserAndContactResponse(User user, List<User> contacts) {
		this.user = user;
		this.contacts = contacts;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getContacts() {
		return contacts;
	}
	public void setContacts(List<User> contacts) {
		this.contacts = contacts;
	}
	
}
