package com.project.authsvc.controller;

//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
//
//import com.project.authsvc.service.UserService;
//import com.project.authsvc.model.User;
//import com.project.authsvc.service.AuthResponse;
//import com.project.authsvc.service.TokenResponse;
//import com.project.authsvc.service.TokenService;
//import com.project.authsvc.service.AuthRequest;


@RestController
@RequestMapping("/home")
public class AuthController {
	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private TokenService tokenService;
//	
	@PostMapping("/login")
	public void new1() {
		System.out.println("hahaa");
	}

//	@PostMapping("/login")
//	public ResponseEntity<Object> login(@RequestBody final User user, final HttpServletRequest request) {
//		
//		final User response = userService.login(user);
//		System.out.println("hahaa");
//		if(response!= null) {
//			String jwt = tokenService.generateToken(response);
//			AuthResponse auth = new AuthResponse();
//			auth.setAuthToken(jwt);
//			return new ResponseEntity<Object>(auth, HttpStatus.OK);
//		}
//		AuthResponse auth = new AuthResponse();
//		auth.setErrorCode("E301");
//		auth.setMessage("Incorrect username/password combination.Please enter valid credentials");
//		return new ResponseEntity<Object>(auth, HttpStatus.UNAUTHORIZED);
//	}

//	@PostMapping("/verify")
//	public ResponseEntity<Object> verify(@RequestBody final AuthRequest authRequest) {
//		
//		AuthResponse response = tokenService.verifyToken(authRequest.getToken());
//		if(StringUtils.isBlank(response.getEmail())) {
//			return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
//		}
//		TokenResponse tokenResponse = new TokenResponse(response.getEmail());
//		return new ResponseEntity<Object>(tokenResponse, HttpStatus.OK);
//
//	}
//
//	
//	@PostMapping("/logout")
//	public ResponseEntity<Object> login(@RequestBody final AuthRequest authRequest) {
//		
//		AuthResponse response = tokenService.verifyToken(authRequest.getToken());
//		if(StringUtils.isBlank(response.getEmail())) {
//			return new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
//		}
//		tokenService.remove(authRequest.getToken());
//		AuthResponse logoutResponse = new AuthResponse();
//		logoutResponse.setMessage("Logged out successfully");
//		return new ResponseEntity<Object>(logoutResponse, HttpStatus.OK);
//
//	}
//	
//	@GetMapping("/tokens")
//	public ResponseEntity<Object> tokens() {
//		return new ResponseEntity<Object>(tokenService.getTokens(), HttpStatus.OK);
//	}
//

}
