$(document).ready(function() {
	numberQuestionsAndAnswers();
	document.getElementById("allQuestions").addEventListener("submit", markQuiz, false);
});

function markQuiz(e){
	e.preventDefault();
	//use this to iterate through and accept that not all questions could be answered
	var questionParents = $(".question");
	console.log(questionParents);
	var answers = [];
	var radioInputs = document.getElementsByClassName("radioAnswer")
	for (var i=0; i<radioInputs.length; i++) {
		if (radioInputs[i].checked) {
			var answer = radioInputs[i].value;
			answers.push(answer);
		}
	}
	var quizId = e.target.getAttribute("data-quiz-id");
	console.log(quizId);
	var data = {
			"answers": answers,
			"quizId": quizId
			};
	var formData = JSON.stringify(data);
	ajaxMarkQuiz(formData);
}

function ajaxMarkQuiz(formData) {
	$.ajax({
		type : "POST",
		url : "/markQuiz",
		data : formData,
		contentType: "application/json; charset=utf-8",
		success : function(result) {
			if (result.status == "200") {
				console.log("success");
				console.log(result);
				//window.location.href = "/yourScore?score="+result.data.score;
			} else {
				console.log("ERROR:  ", "something went wrong");
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}

function numberQuestionsAndAnswers(){
	deleteExistingNumbering();
	var questions = $(".question");
	for (var i=0; i<questions.length; i++) {
		var questionNumber = i + 1;
		var question = $(questions.get(i));
		var questionNumberLabel = document.createElement("DIV");
		questionNumberLabel.setAttribute("class", "questionNumber");
		questionNumberLabel.textContent = "Question " + questionNumber +")";
		question.prepend(questionNumberLabel);
		numberAnswers(question);
	}
}

function numberAnswers(question){
	var answers = question.find(".answer");
	for (var i = 0; i < answers.length ; i++) {
		var letter = (String.fromCharCode(65+i));
		var answer = $(answers.get(i));
		var answerLabel = document.createElement("DIV");
		answerLabel.setAttribute("class", "answerLabel");
		answerLabel.textContent = letter +". ";
		answer.prepend(answerLabel);
	}
}

function deleteExistingNumbering(){
	$(".questionNumber").remove();
	$(".answerLabel").remove();
}