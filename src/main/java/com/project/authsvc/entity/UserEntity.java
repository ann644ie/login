package com.project.authsvc.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "user", schema = "movies")
public class UserEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	 
	@Column(name = "password")
	@Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name = "email")	
	@NotEmpty(message = "*Please provide your password")
	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	 

}
