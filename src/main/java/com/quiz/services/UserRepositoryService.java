package com.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.repositories.UserRepository;
import com.quiz.userObjects.UserType;

@Service
public class UserRepositoryService {
	
	private UserRepository userRepository;
	
	@Autowired
	private UserRepositoryService (UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public String getHashedPasswordForUser(String userType) {
		UserType user = userRepository.findByUserType(userType);
		if (user == null) {
			return null;
		}
		return user.getHashedPassword();
	}

	public void save(UserType userType) {
		userRepository.save(userType);
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
	}

}
