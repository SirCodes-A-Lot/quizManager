package com.quiz.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.quiz.quizObjects.Question;
import com.quiz.quizObjects.QuestionList;
import com.quiz.quizObjects.Quiz;
import com.quiz.statics.QuizConstants;

@Service
public class QuizService {
	
	private static int nextAvailableQuizId = 0;
	
	private QuizRepositoryService quizRepositoryService;
	
	@Autowired
	public QuizService (QuizRepositoryService quizRepositoryService) {
		this.quizRepositoryService  = quizRepositoryService;
	}
	
	public static int getNextAvailableQuizId() {
		nextAvailableQuizId +=1;
		return nextAvailableQuizId;
	}
	
	public void addAllQuizesToModel(Model model) {
		model.addAttribute(QuizConstants.QUIZES, quizRepositoryService.getAllQuizes());
	}

	public void addQuizDataToModel(Model model, String quizId) {
		System.out.println(quizRepositoryService.getQuizById(Integer.parseInt(quizId)).getQuestions());
		model.addAttribute("questions",quizRepositoryService.getQuizById(Integer.parseInt(quizId)).getQuestions());
		model.addAttribute(QuizConstants.SELECTED_QUIZ, quizRepositoryService.getQuizById(Integer.parseInt(quizId)));
	}
	
	public void saveQuizChangesToDatabase(HashMap<String,Object>submittedQuestionData) {
		int quizId = Integer.parseInt((String) submittedQuestionData.get("quizId"));
		Quiz quiz = quizRepositoryService.getQuizById(quizId);
		QuestionList questionList = new QuestionList();
		ArrayList<Object> submittedQuestions = (ArrayList<Object>) submittedQuestionData.get("questions");
		ArrayList<Question> newQuestions = new ArrayList<>();
		for (int i=0; i <submittedQuestions.size(); i++) {
			HashMap<String,Object> questionData = (HashMap<String, Object>) submittedQuestions.get(i);
			String[] answerOptions = ((ArrayList<String>) questionData.get("answers")).toArray(new String[0]);
			String questionText= (String) questionData.get("question");
			String correctAnswer = "need to finish this";
			Question newQuestion = new Question(answerOptions, correctAnswer, questionText, questionList);
			newQuestions.add(newQuestion);
		}
		quiz.setQuestions(questionList);
		quiz.setTitle((String) submittedQuestionData.get("title"));
		questionList.setQuestions(newQuestions);
		quizRepositoryService.update(quiz);
	}

}
