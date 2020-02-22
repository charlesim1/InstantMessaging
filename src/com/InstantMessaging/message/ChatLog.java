package com.InstantMessaging.message;


/*
 * Data that is stored based on the order, the user who sent the message, and the actual
 * message that was sent.
 * 
 */
public class ChatLog {
	public int num = 0;
	public boolean isUser = false;
	public String message = "";
	
	public ChatLog(int num, boolean isUser, String message) {
		this.num = num;
		this.isUser = isUser;
		this.message = message;
	}

	public ChatLog() {
		
	}

	public int getnum() {
		return num;
	}

	public boolean isUser() {
		return isUser;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ChatLog [num=" + num + ", isUser=" + isUser + ", message=" + message + "]";
	}
}
