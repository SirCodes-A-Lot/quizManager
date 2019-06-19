package com.quiz.quizObjects;

import javax.persistence.Entity;
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
    @JoinColumn(name="questions", nullable=false)
	private QuestionList questionList;
	
	
	public Question() {
	}

	public Question(String[] answerOptions, String correctAnswer, String question, QuestionList questionList) {
		this.answerOptions = answerOptions;
		this.correctAnswer = correctAnswer;
		this.question = question;
		this.questionList = questionList;
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
	
	public int getId() {
		return id;
	}

}
