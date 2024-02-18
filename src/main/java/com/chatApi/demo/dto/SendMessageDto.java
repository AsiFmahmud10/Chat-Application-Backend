package com.chatApi.demo.dto;

public class SendMessageDto {
	private long senderId;
	private long receiverId;
	private String msg;

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "SendMessageDto [senderId=" + senderId + ", recieverId=" + receiverId + ", msg=" + msg + "]";
	}

}
