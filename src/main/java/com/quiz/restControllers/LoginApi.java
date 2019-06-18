package com.quiz.restControllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.quizObjects.Response;
import com.quiz.services.LoginService;
import com.quiz.statics.QuizConstants;

@RestController
public class LoginApi {
	
	private LoginService loginService;
	
	public LoginApi(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@PostMapping("/login")
	public Response login(@RequestBody HashMap<String,Object>data, HttpServletRequest request) {
		Response response = new Response();
		HashMap<String, Object> responseData = new HashMap<>();
		String userType = (String) data.get(QuizConstants.USER_TYPE);
		String password = (String) data.get(QuizConstants.PASSWORD);
		System.out.println(data);
		System.out.println(userType + password);
		if (loginService.isPasswordValid(userType, password)) {
			responseData.put(QuizConstants.LOGIN, QuizConstants.SUCCESS);
			response.setStatus("200");
			request.getSession().setAttribute(QuizConstants.ACCESS, userType);
		} else {
			response.setStatus("401");
		}
		response.setData(responseData);
		return response;
	}

}
