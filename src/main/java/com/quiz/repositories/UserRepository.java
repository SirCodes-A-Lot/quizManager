package com.quiz.repositories;

import org.springframework.data.repository.CrudRepository;

import com.quiz.userObjects.UserType;

public interface UserRepository extends CrudRepository<UserType, Integer> {

	public UserType findByUserType(String userType);

}
