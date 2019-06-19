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
		System.out.println(id);
		Iterator<Quiz> quizes = quizRepository.findAll().iterator();
		while (quizes.hasNext()) {
			System.out.println("quiz id " + quizes.next().getQuizId() + quizes.next().getTitle());
		}
		System.out.println(quizRepository.findAll());
		return quizRepository.findById(id).get();
	}
}
