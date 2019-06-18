package com.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.quiz.quizObjects.Quiz;
import com.quiz.statics.QuizConstants;

@Service
public class QuizService {
	
	private QuizRepositoryService quizRepositoryService;
	
	@Autowired
	public QuizService (QuizRepositoryService quizRepositoryService) {
		this.quizRepositoryService  = quizRepositoryService;
	}
	
	public void addAllQuizesToModel(Model model) {
		model.addAttribute(QuizConstants.QUIZES, quizRepositoryService.getAllQuizes());
	}

	public void addQuizDataToModel(Model model, String title) {
		System.out.println(quizRepositoryService.getQuizByTitle(title).getQuestions());
		model.addAttribute("questions",quizRepositoryService.getQuizByTitle(title).getQuestions());
		model.addAttribute(QuizConstants.SELECTED_QUIZ, quizRepositoryService.getQuizByTitle(title));
	}

}
