	package com.demo.crud.entities;

public class PojoAddComment {

	private String text;
	private String user;
	private Long phoneId;
	
	public PojoAddComment() {}
	
	public PojoAddComment(String text, String user, Long phoneId) {
		super();
		this.text = text;
		this.user = user;
		this.phoneId = phoneId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}
	
}
