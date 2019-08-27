package com.project.authsvc.service;
import java.io.Serializable;
public class AuthRequest  implements Serializable{

	private static final long serialVersionUID = 426120178271409123L;
	
	private String token;
	
	public String getToken() {
		return this.token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

}
