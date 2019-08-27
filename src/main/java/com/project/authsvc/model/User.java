package com.project.authsvc.model;
import java.io.Serializable;


public class User implements Serializable{
	private static final long serialVersionUID = -7904576225217295165L;
	

	private Long userId;
	private String email;
	private String password;
	private String name;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
	

}
