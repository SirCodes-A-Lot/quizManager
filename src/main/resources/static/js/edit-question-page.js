var temporaryIdInt = 1;
$(document).ready(function() {
	document.getElementById("editQuiz").addEventListener("submit", saveQuizSubmit, false);
	document.getElementById("newQuestionForm").addEventListener("submit", addQuestion, false);
	setDeleteQuestionHandler();
	setAddAnswerHandler();
	setDeleteAnswerHandler();
	populateAddQuestionDropDown();
});

function setDeleteAnswerHandler() {
	var deleteAnswerButtons = document.getElementsByClassName("deleteAnswer");
	for (var i = 0; i < deleteAnswerButtons.length; i++) {
		deleteAnswerButtons[i].addEventListener("click", deleteAnswer, false);
	}
}

function setAddAnswerHandler() {
	var addAnswerButtons = document.getElementsByClassName("addAnswer");
	for (var i = 0; i < addAnswerButtons.length; i++) {
		addAnswerButtons[i].addEventListener("click", addAnswer, false);
	}
}

function setDeleteQuestionHandler() {
	var deleteButtons = document.getElementsByClassName("deleteQuestion");
	for (var i = 0; i < deleteButtons.length; i++) {
		deleteButtons[i].addEventListener("click", deleteQuestion, false);
	}
}

function deleteAnswer(e) {
	e.preventDefault();
	var answer = e.target.parentElement;
	var question = answer.parentNode;
	if (question.getElementsByClassName("answer").length > 2){
		question.removeChild(answer);
		numberQuestionsAndAnswers();
	}
}

function addAnswer(e) {
	e.preventDefault();
	var parentQuestion = e.target.parentElement;
	var answerDivs = e.target.parentElement.getElementsByClassName("answer");
	var firstAnswer = answerDivs[0];
	var parentQuestionId = parentQuestion.getAttribute("data-question-id");
	parentQuestion.insertBefore(createAnswer(parentQuestionId), firstAnswer);
    numberQuestionsAndAnswers();
    setDeleteAnswerHandler();
}

function createAnswer(parentQuestionId){
	console.log("createAnswer");
	var newAnswer = document.createElement("DIV");
	newAnswer.setAttribute("class", "answer");
	var newAnswerContent = "<input class='radioAnswer' type='radio' name='" + parentQuestionId + "' value='notSet'></input>" +
	"<input class='answerTextInput'></input>"+
	"<button class='deleteAnswer'>Delete Answer</button>";
	newAnswer.innerHTML = newAnswerContent;
	return newAnswer;
}

function addQuestion(e) {
	e.preventDefault();
	var questionDiv = $("#allQuestions");
	var question = makeDefaultQuestion();
	var position = 	document.getElementById("newQuestionDropDown").value-1;
    if(position === 0) {
    	$("#allQuestions").prepend(question);
    } else {
    	$("#allQuestions > div:nth-child(" + (position) + ")").after(question);
    }
    numberQuestionsAndAnswers();
    populateAddQuestionDropDown();
    setDeleteQuestionHandler();
    setAddAnswerHandler();
    setDeleteAnswerHandler();
}

function makeDefaultQuestion(){
	var newQuestion = document.createElement("DIV");
	newQuestion.setAttribute("class", "question");
	newQuestion.setAttribute("data-question-id", "newId" + temporaryIdInt);
	newQuestion.innerHTML= ""+
			"<textarea class='questionInput'>New Question</textarea>" +
		  	"<div class='answer'>" +
		   		"<input class='radioAnswer' type='radio' name='newId" + temporaryIdInt + "' value= 'notSet' checked></input>" +
				"<input class='answerTextInput' th:value='notSet'></input>" +
				"<button class='deleteAnswer'>Delete Answer</button>" +
			"</div>" +
		  	"<div class='answer'>" +
	   			"<input class='radioAnswer' type='radio' name='newId" + temporaryIdInt + "' value= 'notSet'></input>" +
	   			"<input class='answerTextInput' th:value='notSet'></input>" +
	   			"<button class='deleteAnswer'>Delete Answer</button>" +
			"</div>" +
			 "<button class='addAnswer'>Add Answer</button>" +
			 "<button class='deleteQuestion'>Delete Question</button>";
	
	console.log(newQuestion);
	temporaryIdInt +=1;
	return newQuestion;
}

function populateAddQuestionDropDown(){
	var numberQuestions = $(".question").length;
	var dropDown = document.getElementById("newQuestionDropDown");
	while (dropDown.firstChild) {
		dropDown.removeChild(dropDown.firstChild);
	}
	for (var i=1; i<numberQuestions +2; i++) {
		var option = document.createElement("OPTION");
		option.value=i;
		option.text=i;
		dropDown.append(option);
	}
}

function deleteQuestion(e) {
	e.preventDefault();
	var question = e.target.parentElement;
	question.parentNode.removeChild(question);
	populateAddQuestionDropDown();
	numberQuestionsAndAnswers();
}

function saveQuizSubmit(e){
	e.preventDefault();
	ajaxSubmitQuiz(getQuizData());
}

function getQuizData() {
	var data = {
			"quizId":getQuizId(),
			"title": getTitle(),
			"description": getDescription(),
			"questions": getQuestions()
	};
	var formData = JSON.stringify(data);
	return formData;
}

function getQuizId() {
	var quiz = document.getElementById("allQuestions");
	return quiz.getAttribute("data-quiz-id");
}

function getTitle() {
	var title = document.getElementById("titleInput");
	return title.value;
}

function getDescription() {
	var description = document.getElementById("descriptionInput");
	return description.value;
}

function getQuestions(){
	var questionParents = $(".question");
	var questions = [];
	for (var i=0; i<questionParents.length; i++) {
		var questionParent = questionParents.get(i);
		var questionInput = questionParent.getElementsByClassName("questionInput")[0];
		var questionData = {
				"question":questionInput.value,
				"answers":getQuestionAnswers(questionParent),
				"correctAnswer":getCorrectAnswer(questionParent)
		}
		console.log(questionData);
		questions.push(questionData);
	}
	return questions;
}

function getCorrectAnswer(questionParent) {
	var answerInputs = questionParent.getElementsByClassName("answerTextInput");
	var radioInputs = questionParent.getElementsByClassName("radioAnswer")
	for (var i=0; i<answerInputs.length; i++) {
		if (radioInputs[i].checked) {
			var answer = answerInputs[i].value;
			console.log(answer);
			return answer;
		}
	}
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