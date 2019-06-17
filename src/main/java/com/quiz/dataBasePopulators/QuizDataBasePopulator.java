package com.quiz.dataBasePopulators;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.quiz.quizObjects.Question;
import com.quiz.quizObjects.Quiz;
import com.quiz.services.LoginService;
import com.quiz.services.QuizRepositoryService;
import com.quiz.services.UserRepositoryService;
import com.quiz.userObjects.UserType;

@Service
public class QuizDataBasePopulator {
	
	private QuizRepositoryService quizRepositoryService;
	
	private UserRepositoryService userRepositoryService;
	
	private LoginService loginService;
	
	private Quiz quiz1 = new Quiz();
	
	private String[] question1answers = {"red","green","blue"};
	
	private String[] question2answers = {"true","false"};
	
	private Question question1 = new Question(question1answers, "red", "What colour is a red fire truck?", quiz1);
	
	private Question question2 = new Question(question2answers, "true", "Apples can be red", quiz1);
	
	@Autowired
	public QuizDataBasePopulator (QuizRepositoryService quizRepositoryService, UserRepositoryService userRepositoryService,
			LoginService loginService) {
		this.quizRepositoryService = quizRepositoryService;
		this.userRepositoryService = userRepositoryService;
		this.loginService = loginService;
	}
	
	private ArrayList<Quiz> getDefaultQuizes() {
		ArrayList<Quiz> quizes = new ArrayList<Quiz>();
		quiz1.setQuestions(new ArrayList<Question>( Arrays.asList(question1, question2)));
		quiz1.setId(1);
		quiz1.setTitle("Colours");
		quiz1.setDescription("What colour is...");
		
		quizes.add(quiz1);
		return quizes;
	}
	
	private ArrayList<UserType> getDefaultUsers() {
		ArrayList<UserType> users = new ArrayList<>();
		users.add(new UserType("Restricted", loginService.encryptString("Restricted")));
		users.add(new UserType("View", loginService.encryptString("View")));
		users.add(new UserType("Edit", loginService.encryptString("Edit")));
		return users;
	}
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void setQuizAndUsersInDataBaseAfterStartup() {
		ArrayList<Quiz> quizes = getDefaultQuizes();
		for (int i =0; i< quizes.size(); i++){
			quizRepositoryService.save(quizes.get(i));
		}
		ArrayList<UserType> users = getDefaultUsers();
		for (int i =0; i< users.size(); i++){
			userRepositoryService.save(users.get(i));
		}
	}
}
