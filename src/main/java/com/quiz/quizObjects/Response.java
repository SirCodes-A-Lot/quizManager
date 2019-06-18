package com.quiz.quizObjects;

import java.util.HashMap;

public class Response {
	
	private String status;
	private HashMap<String, Object> data;

	public Response() {
	}
	
	public Response (String status, HashMap <String, Object> data) {
		this.status = status;
		this.data = data;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}
	
}
