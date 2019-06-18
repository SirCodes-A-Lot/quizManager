$(document).ready(function() {
	document.getElementById("editQuiz").addEventListener("submit", saveQuizSubmit, false);
});

function saveQuizSubmit(e){
	e.preventDefault();
	ajaxSubmitQuiz(getQuizData());
}

function getQuizData() {
	var data = {
			"title": getTitle(),
			"questions" : getQuestions()
	};
	console.log(data);
	var formData = JSON.stringify(data);
	return formData;
}

function getTitle() {
	var title = document.getElementById("titleInput");
	return title.value;
}

function getQuestions(){
	var questionParents = $(".question");
	console.log(questionParents);
	var questions = [];
	for (var i=0; i<questionParents.length; i++) {
		var questionParent = questionParents.get(i);
		var questionInput = questionParent.getElementsByClassName("questionInput")[0];
		var questionData = {
				"question":questionInput.value,
				"answers":getQuestionAnswers(questionParent)
		}
		console.log(questionInput);
		questions.push(questionData);
	}
	return questions;
}

function getQuestionAnswers(questionParent) {
	var answers = [];
	var answerInputs = questionParent.getElementsByClassName("answerTextInput");
	for (var i=0; i<answerInputs.length; i++) {
		var answer = answerInputs[i].value;
		answers.push(answer);
	}
	return answers;
}

function ajaxSubmitQuiz(formData) {
	$.ajax({
		type : "POST",
		url : "/saveQuiz",
		data : formData,
		contentType: "application/json; charset=utf-8",
		success : function(result) {
			if (result.status == "200") {
				console.log("success");
			} else {
				console.log("ERROR:  ", "something went wrong");
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}