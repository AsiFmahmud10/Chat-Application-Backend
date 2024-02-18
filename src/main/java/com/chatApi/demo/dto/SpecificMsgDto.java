package com.chatApi.demo.dto;

public class SpecificMsgDto {
	
	private long senderId;
	private long receiverId;
	
	public long getSenderId() {
		return senderId;
	}
	public void setSenderId(long senderID) {
		this.senderId = senderID;
	}
	public long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}
	@Override
	public String toString() {
		return "SpecificMsgDto [senderID=" + senderId + ", receiverId=" + receiverId + "]";
	}
 
	
}
