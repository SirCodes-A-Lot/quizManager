package com.quiz.quizObjects;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Quiz {
	
	@Id
	private int quizId;
	
	private String title;
	
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="questionId")
	private QuestionList questions;
	
	public Quiz() {
	}

	public Quiz(int quizId, String title, String description, QuestionList questions) {
		this.quizId = quizId;
		this.title = title;
		this.description = description;
		this.questions = questions;
	}

	public Integer getQuizId() {
		return quizId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public QuestionList getQuestions() {
		return questions;
	}

	public void setQuestions(QuestionList questions) {
		this.questions = questions;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
		
	}

	
}
