package com.quiz.pageControllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.quiz.statics.QuizConstants;

@Controller
public class PageControllers {
	
	@GetMapping("/home")
	public String getHomePage(HttpServletRequest request) {
		System.out.println("accessing /home");
		if (isLoggedIn(request)) {
			return "home";
		} else {
			return "index";
		}
	}
	
	@GetMapping("/viewQuiz")
	public String getQuizPage(HttpServletRequest request) {
		if (isLoggedIn(request)) {
			return "viewQuestions";
		} else {
			return "index";
		}
	}
	
	@GetMapping("/editQuiz")
	public String getEditPage(HttpServletRequest request) {
		if (isEditor(request)) {
			return "editQuestions";
		} else {
			return "index";
		}
	}
	
	private boolean isLoggedIn (HttpServletRequest request) {
		String userType = (String) request.getSession().getAttribute(QuizConstants.ACCESS);
		System.out.println(userType);
		if (userType != null && (
				userType.contentEquals(QuizConstants.RESTRICTED) ||
				userType.contentEquals(QuizConstants.VIEW) ||
				userType.contentEquals(QuizConstants.EDIT))) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isEditor (HttpServletRequest request) {
		String userType = (String) request.getSession().getAttribute(QuizConstants.USER_TYPE);
		if (userType != null && userType.contentEquals(QuizConstants.EDIT)) {
			return true;
		} else {
			return false;
		}
	}
}
