package com.quiz.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.quiz.quizObjects.Quiz;
import com.quiz.repositories.QuizRepository;

public class QuizRepositoryService {
	
	private QuizRepository quizRepository;

	@Autowired
	public QuizRepositoryService(QuizRepository quizRepository) {
		this.quizRepository = quizRepository;
	}
	
	public ArrayList<Quiz> getAllQuizes() {
		ArrayList <Quiz> quizes = new ArrayList<Quiz>();
		for (Quiz quiz : quizRepository.findAll()) {
			quizes.add(quiz);
		}
		return quizes;
	}
}
