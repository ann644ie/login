package com.project.authsvc.service;
import java.util.List;
import com.project.authsvc.model.User;
import com.project.authsvc.service.AuthResponse;
public interface TokenService {

	String generateToken(User user);
	List<String> getTokens();
	AuthResponse verifyToken(String token);
	void remove(String token);

}
