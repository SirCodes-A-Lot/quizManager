package com.quiz.quizObjects;

import java.util.ArrayList;

public class Question {
	
	private int id;
	
	private ArrayList<String> answerOptions;
	
	private String correctAnswer;
	
	private String question;
	
	public Question() {
	}

	public Question(int id, ArrayList<String> answerOptions, String correctAnswer, String question) {
		this.id = id;
		this.answerOptions = answerOptions;
		this.correctAnswer = correctAnswer;
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public ArrayList<String> getAnswerOptions() {
		return answerOptions;
	}

	public void setAnswerOptions(ArrayList<String> answerOptions) {
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
