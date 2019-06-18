package com.quiz.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.quizObjects.Quiz;
import com.quiz.repositories.QuizRepository;

@Service
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
	
	public void save(Quiz quiz) {
		quizRepository.save(quiz);
	}
	
	public void deleteAll() {
		quizRepository.deleteAll();
	}

	public Quiz getQuizByTitle(String title) {
		return quizRepository.findByTitle(title);
	}
}
