package com.project.authsvc.service;
import java.util.List;
import com.project.authsvc.model.User;
public interface UserService {

	
	User save(User user);
	User login(User user);
	List<User> loadAll();
	User findByEmail(String email);
}
