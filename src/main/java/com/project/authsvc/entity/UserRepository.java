package com.project.authsvc.entity;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	

	UserEntity findByEmail(String email);
	
	//UserEntity findById(String id);


}
