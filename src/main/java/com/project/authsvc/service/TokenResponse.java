package com.project.authsvc.service;

import java.io.Serializable;

public class TokenResponse  implements Serializable{
	
	private static final long serialVersionUID = 624740686287392471L;

	private String email;

	public TokenResponse(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

}
