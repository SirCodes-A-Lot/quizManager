package com.quiz.dataBasePopulators;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.quiz.quizObjects.Question;
import com.quiz.quizObjects.Quiz;
import com.quiz.services.QuizRepositoryService;

@Service
public class QuizDataBasePopulator {
	
	private QuizRepositoryService quizRepositoryService;
	
	private Quiz quiz1 = new Quiz();
	
	private String[] question1answers = {"red","green","blue"};
	
	private Question question1 = new Question(question1answers, "red", "What colour is a red fire truck?", quiz1);
	
	private Question question2 = new Question(question1answers, "true", "Apples can be red", quiz1);
	
	@Autowired
	public QuizDataBasePopulator (QuizRepositoryService quizRepositoryService) {
		this.quizRepositoryService = quizRepositoryService;
	}
	
	private ArrayList<Quiz> getDefaultQuizes() {
		ArrayList<Quiz> quizes = new ArrayList<Quiz>();
		quiz1.setQuestions(new ArrayList<Question>( Arrays.asList(question1)));
		quiz1.setId(1);
		quiz1.setTitle("Colours");
		quiz1.setDescription("What colour is...");
		
		quizes.add(quiz1);
		return quizes;
	}
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void setProductsInDataBaseAfterStartup() {
		ArrayList<Quiz> quizes = getDefaultQuizes();
		for (int i =0; i< quizes.size(); i++){
			quizRepositoryService.save(quizes.get(i));
		}
	}

}
