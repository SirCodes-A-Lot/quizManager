package com.quiz.dataBasePopulators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.quiz.quizObjects.Question;
import com.quiz.quizObjects.QuestionList;
import com.quiz.quizObjects.Quiz;
import com.quiz.services.LoginService;
import com.quiz.services.QuizRepositoryService;
import com.quiz.services.QuizService;
import com.quiz.services.UserRepositoryService;
import com.quiz.userObjects.UserType;

@Service
public class QuizDataBasePopulator {
	
	private QuizRepositoryService quizRepositoryService;
	
	private UserRepositoryService userRepositoryService;
	
	private LoginService loginService;
	
	@Autowired
	public QuizDataBasePopulator (QuizRepositoryService quizRepositoryService, UserRepositoryService userRepositoryService,
			LoginService loginService) {
		this.quizRepositoryService = quizRepositoryService;
		this.userRepositoryService = userRepositoryService;
		this.loginService = loginService;
	}
	
	private ArrayList<Quiz> getDefaultQuizes() {
		ArrayList<Quiz> quizes = new ArrayList<Quiz>();
		Quiz quiz1 = new Quiz();
		QuestionList questionList1 = new QuestionList();
		String[] question1answers = {"red","green","blue"};
		Question question1 = new Question(question1answers, "red", "What colour is a red fire truck?", questionList1);
		String[] question2answers = {"true","false"};
		Question question2 = new Question(question2answers, "true", "Apples can be red, true or false?", questionList1);
		questionList1.setQuestions(new ArrayList<Question>(Arrays.asList(question1, question2)));
		quiz1.setQuizId(QuizService.getNextAvailableQuizId());
		quiz1.setQuestions(questionList1);
		quiz1.setTitle("Colours");
		quiz1.setDescription("What colour is...");
		

		Quiz quiz2 = new Quiz();
		QuestionList questionList2 = new QuestionList();
		String[] question3answers = {"red","green","blue"};
		Question question3 = new Question(question3answers, "green", "What colour is a green fire truck?", questionList2);
		questionList2.setQuestions(new ArrayList<Question>( Arrays.asList(question3)));
		quiz2.setQuizId(QuizService.getNextAvailableQuizId());
		quiz2.setQuestions(questionList2);
		quiz2.setTitle("Fire trucks");
		quiz2.setDescription("Do you know your fire trucks?");
		
		quizes.add(quiz1);
		quizes.add(quiz2);
		
		return quizes;
	}
	
	private ArrayList<UserType> getDefaultUsers() {
		ArrayList<UserType> users = new ArrayList<>();
		String restricted = loginService.encryptString("Restricted");
		String view = loginService.encryptString("View");
		String edit = loginService.encryptString("Edit");
		users.add(new UserType("Restricted", restricted));
		users.add(new UserType("View", view));
		users.add(new UserType("Edit", edit));
		return users;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void setQuizAndUsersInDataBaseAfterStartup() {
		quizRepositoryService.deleteAll();
		userRepositoryService.deleteAll();
		ArrayList<Quiz> quizes = getDefaultQuizes();
		for (int i =0; i< quizes.size(); i++){
			quizRepositoryService.save(quizes.get(i));
		}
		
		Iterator<Quiz> savedQuizes = quizRepositoryService.getAllQuizes().iterator();
		while (savedQuizes.hasNext()) {
			Quiz quiz = savedQuizes.next();
			System.out.println("quiz id " + quiz.getQuizId() + quiz.getTitle());
		}
		ArrayList<UserType> users = getDefaultUsers();
		for (int i =0; i< users.size(); i++){
			userRepositoryService.save(users.get(i));
		}
	}
}
