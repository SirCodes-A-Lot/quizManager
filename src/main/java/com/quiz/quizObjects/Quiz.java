package com.quiz.quizObjects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Quiz {
	
	@Id
	private int id;
	
	private String title;
	
	private String description;
	
	@OneToMany(mappedBy="quiz")
	private List<Question> questions;
	
	//@OneToMany(targetEntity=Question.class, mappedBy="quiz", fetch=FetchType.EAGER)
	//private List<Question> questions;
	
	public Quiz() {
	}

	public Quiz(int id, String title, String description, List<Question> questions) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	
}