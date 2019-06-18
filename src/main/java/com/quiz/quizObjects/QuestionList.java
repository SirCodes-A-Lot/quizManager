package com.quiz.quizObjects;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class QuestionList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	
	//@OneToMany(targetEntity=Question.class, mappedBy="questionList", fetch=FetchType.EAGER)
	//@OneToMany(mappedBy="question", fetch=FetchType.EAGER)
	@OneToMany(targetEntity = Question.class, mappedBy = "questionList", 
		    cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Question> questions;
	
	
	public QuestionList() {
	}
	
	public QuestionList(List<Question> questions) {
		this.questions = questions;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
