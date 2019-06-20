package com.quiz.services;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.quiz.statics.QuizConstants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserValidationServiceTest {
	
	@Mock
	private HttpServletRequest mockHttpServletRequest;
	
	@Mock
	private HttpSession mockHttpSession;
	
	private UserValidationService userValidationService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		userValidationService = new UserValidationService();
		given(mockHttpServletRequest.getSession()).willReturn(mockHttpSession);
	}
	
	@Test
	public void givenUserIsRestrictedThenOnlyIsLoggedInReturnsTrue(){
		given(mockHttpSession.getAttribute(QuizConstants.ACCESS)).willReturn(QuizConstants.RESTRICTED);
		assertTrue(userValidationService.isLoggedIn(mockHttpServletRequest));
		assertFalse(userValidationService.isEditorOrView(mockHttpServletRequest));
		assertFalse(userValidationService.isEditor(mockHttpServletRequest));
	}
	
	@Test
	public void givenUserIsViewThenOnlyIsLoggedInAndIsEditorOrViewReturnTrue(){
		given(mockHttpSession.getAttribute(QuizConstants.ACCESS)).willReturn(QuizConstants.VIEW);
		assertTrue(userValidationService.isLoggedIn(mockHttpServletRequest));
		assertTrue(userValidationService.isEditorOrView(mockHttpServletRequest));
		assertFalse(userValidationService.isEditor(mockHttpServletRequest));
	}
	
	@Test
	public void givenUserIsEditorThenAllChecksReturnTrue(){
		given(mockHttpSession.getAttribute(QuizConstants.ACCESS)).willReturn(QuizConstants.EDIT);
		assertTrue(userValidationService.isLoggedIn(mockHttpServletRequest));
		assertTrue(userValidationService.isEditorOrView(mockHttpServletRequest));
		assertTrue(userValidationService.isEditor(mockHttpServletRequest));
	}
	
	@Test
	public void givenUserIsNullThenAllChecksReturnFalse(){
		given(mockHttpSession.getAttribute(QuizConstants.ACCESS)).willReturn(null);
		assertFalse(userValidationService.isLoggedIn(mockHttpServletRequest));
		assertFalse(userValidationService.isEditorOrView(mockHttpServletRequest));
		assertFalse(userValidationService.isEditor(mockHttpServletRequest));
	}
	
	@Test
	public void givenUserTypeIsNotValidThenAllChecksReturnFalse(){
		given(mockHttpSession.getAttribute(QuizConstants.ACCESS)).willReturn("invalid");
		assertFalse(userValidationService.isLoggedIn(mockHttpServletRequest));
		assertFalse(userValidationService.isEditorOrView(mockHttpServletRequest));
		assertFalse(userValidationService.isEditor(mockHttpServletRequest));
	}

}
