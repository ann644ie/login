package com.project.authsvc.service;
import java.io.Serializable;
public class AuthResponse implements Serializable {

	private static final long serialVersionUID = -3072153816723566453L;
	private String authToken;
	private String message;
	private String errorCode;
	private String email;
	
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
	
}
