package com.quiz.restControllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizObjects.Response;
import com.quiz.services.QuizService;
import com.quiz.services.UserValidationService;

@RestController
public class QuizApis {
	
	private QuizService quizService;
	
	private UserValidationService userValidationService;
	
	@Autowired
	public QuizApis (QuizService quizService, UserValidationService userValidationService) {
		this.quizService = quizService;
		this.userValidationService = userValidationService;
	}

	@PostMapping("/saveQuiz")
	public Response saveQuiz(@RequestBody HashMap<String,Object>requestData, HttpServletRequest request) {
		Response response = new Response();
		if (userValidationService.isEditor(request)) {
			quizService.saveQuizChangesToDatabase(requestData);
			response.setStatus("200");
		} else {
			response.setStatus("401");
		}
		return response;
	}
	
	@PostMapping("/addQuiz")
	public Response addQuiz(HttpServletRequest request) {
		Response response = new Response();
		if (userValidationService.isEditor(request)) {
			quizService.makeNewDefaultQuiz();
			response.setStatus("200");
		} else {
			response.setStatus("401");
		}
		return response;
	}

	@DeleteMapping("/deleteQuiz")
	public Response deleteQuiz(@RequestBody HashMap<String,Object>requestData, HttpServletRequest request) {
		Response response = new Response();
		if (userValidationService.isEditor(request)) {
			quizService.deleteQuiz(requestData);
			response.setStatus("200");
		} else {
			response.setStatus("401");
		}
		return response;
	}
	
	@PostMapping("/markQuiz")
	public Response markQuiz(@RequestBody HashMap<String,Object>requestData, HttpServletRequest request) {
		Response response = new Response();
		if (userValidationService.isEditorOrView(request)) {
			HashMap<String, Integer>responseData = new HashMap<>();
			responseData.put("score", quizService.markQuiz(requestData));
			response.setStatus("200");
		} else {
			response.setStatus("401");
		}
		return response;
	}
}
