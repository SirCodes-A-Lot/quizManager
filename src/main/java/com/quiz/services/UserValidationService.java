package com.quiz.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.quiz.statics.QuizConstants;

@Service
public class UserValidationService {
	
	public boolean isLoggedIn (HttpServletRequest request) {
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
	
	public boolean isEditorOrView (HttpServletRequest request) {
		String userType = (String) request.getSession().getAttribute(QuizConstants.ACCESS);
		if (userType != null && (
				userType.contentEquals(QuizConstants.VIEW) ||
				userType.contentEquals(QuizConstants.EDIT))) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEditor (HttpServletRequest request) {
		String userType = (String) request.getSession().getAttribute(QuizConstants.ACCESS);
		if (userType != null && userType.contentEquals(QuizConstants.EDIT)) {
			return true;
		} else {
			return false;
		}
	}

}
