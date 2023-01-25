package com.onlinepizza.exception;

import java.time.LocalDateTime;

public class ErrorInformation {
	private String url;
	private String msg;
	private LocalDateTime dateTime;
	
	public ErrorInformation(String url, String msg, LocalDateTime dateTime) {
		super();
		this.url = url;
		this.msg = msg;
		this.dateTime = dateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	

}
