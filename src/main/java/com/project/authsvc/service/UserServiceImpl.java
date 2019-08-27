package com.project.authsvc.service;

import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import com.project.authsvc.entity.UserEntity;
import com.project.authsvc.entity.UserRepository;
import com.project.authsvc.model.User;



@Component
public class UserServiceImpl implements UserService  {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ConversionService conversionService;


	@Override
	@Transactional
	public User save(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		final UserEntity userEntity = conversionService.convert(user, UserEntity.class);
		final UserEntity response = userRepository.save(userEntity);
		return user;
	}
	
	@Override
	public User login(User user) {
		final UserEntity userEntity = userRepository.findByEmail(user.getEmail());
		boolean isAuthenticatedUser = bCryptPasswordEncoder.matches(user.getPassword(), userEntity.getPassword());
		if(isAuthenticatedUser) {
			final User response = conversionService.convert(userEntity, User.class);
			response.setPassword(null);
			return response;
		}
		return null;
	}

	
	@Override
	public List<User> loadAll() {
		List<UserEntity> userEntities = userRepository.findAll();
		final List<User> users = new ArrayList<>();
		if(!CollectionUtils.isEmpty(userEntities)) {
			userEntities.stream().forEach(ue -> {
				users.add(conversionService.convert(ue, User.class));
			});
		}
		return users;
	}


	@Override
	public User findByEmail(String email) {
		final UserEntity userEntity = userRepository.findByEmail(email);
		final User response = conversionService.convert(userEntity, User.class);
		response.setUserId(userEntity.getId());
		return response;
	}



}
