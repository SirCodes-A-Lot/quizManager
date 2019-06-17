package com.quiz.quizObjects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String[] answerOptions;
	
	private String correctAnswer;
	
	private String question;
	
    @ManyToOne
    @JoinColumn(name="quiz_id", nullable=false)
	private Quiz quiz;
	
	
	public Question() {
	}

	public Question(String[] answerOptions, String correctAnswer, String question, Quiz quiz) {
		this.answerOptions = answerOptions;
		this.correctAnswer = correctAnswer;
		this.question = question;
		this.quiz = quiz;
	}

	public String[] getAnswerOptions() {
		return answerOptions;
	}

	public void setAnswerOptions(String[] answerOptions) {
		this.answerOptions = answerOptions;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
