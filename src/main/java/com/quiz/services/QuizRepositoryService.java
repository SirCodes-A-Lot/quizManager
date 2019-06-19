package com.quiz.services;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	public void update(Quiz quiz) {
		quizRepository.deleteById(quiz.getQuizId());
		quizRepository.save(quiz);
		System.out.println(quiz.getQuizId());
	}

	public Quiz getQuizByTitle(String title) {
		return quizRepository.findByTitle(title);
	}
	
	public Quiz getQuizById(int id) {
		return quizRepository.findById(id).get();
	}

	public void deleteQuizById(int quizId) {
		quizRepository.deleteById(quizId);
	}
}
