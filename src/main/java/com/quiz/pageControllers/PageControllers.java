package com.quiz.pageControllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.services.QuizService;
import com.quiz.statics.QuizConstants;

@Controller
public class PageControllers {
	
	private QuizService quizService;
	
	@Autowired
	public PageControllers (QuizService quizService) {
		this.quizService = quizService;
	}
	
	@GetMapping("/home")
	public String getHomePage(HttpServletRequest request, Model model) {
		System.out.println("accessing /home");
		if (isLoggedIn(request)) {
			quizService.addAllQuizesToModel(model);
			return "home";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/viewQuiz")
	public String getQuizPage(HttpServletRequest request, @RequestParam String quizTitle, Model model) {
		if (isLoggedIn(request)) {
			quizService.addQuizDataToModel(model, quizTitle);
			return "viewQuestions";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/editQuiz")
	public String getEditPage(HttpServletRequest request, @RequestParam String quizTitle, Model model) {
		if (isEditor(request)) {
			quizService.addQuizDataToModel(model, quizTitle);
			return "editQuestions";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("/")
	public String logoutGetLoginPage(HttpServletRequest request) {
		request.getSession().setAttribute(QuizConstants.ACCESS, null);
		return "index";
	}
	
	private boolean isLoggedIn (HttpServletRequest request) {
		String userType = (String) request.getSession().getAttribute(QuizConstants.ACCESS);
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
		String userType = (String) request.getSession().getAttribute(QuizConstants.ACCESS);
		if (userType != null && userType.contentEquals(QuizConstants.EDIT)) {
			return true;
		} else {
			return false;
		}
	}
}
