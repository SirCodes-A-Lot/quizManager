package com.quiz.services;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	private UserRepositoryService userRepositoryService;
	
	@Autowired
	public LoginService (UserRepositoryService userRepositoryService) {
		this.userRepositoryService = userRepositoryService;
	}
	
	public boolean isPasswordValid(String userType, String password) {
		String storedPassword = userRepositoryService.getHashedPasswordForUser(userType);
		if (storedPassword == null) {
			return false;
		}
		String hashedInputPassword = encryptString(password);
		if (storedPassword.contentEquals(hashedInputPassword)) {
			return true;
		}
		return false;
	}
	
	public String encryptString(String unencryptedString) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(unencryptedString.getBytes());
			return new String (messageDigest.digest());
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
