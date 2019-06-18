package com.quiz.repositories;

import org.springframework.data.repository.CrudRepository;

import com.quiz.quizObjects.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {

	public Quiz findByTitle(String title);

}
