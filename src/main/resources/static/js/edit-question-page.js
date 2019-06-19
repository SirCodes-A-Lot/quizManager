var newQuestionTemporaryIdInt = 1;
$(document).ready(function() {
	document.getElementById("editQuiz").addEventListener("submit", saveQuizSubmit, false);
	document.getElementById("deleteQuestion").addEventListener("click", deleteQuestion, false);
	document.getElementById("newQuestionForm").addEventListener("submit", addQuestion, false);
	populateAddQuestionDropDown();
});

function addQuestion(e){
	e.preventDefault();
	var questionDiv = $("#allQuestions");
	var question = makeDefaultQuestion();
	var position = 	document.getElementById("newQuestionDropDown").value-1;
	//if (position <= $(".question").length){
	//	console.log(question);
	//	//questionDiv.prepend(question);
	//	questionDiv.insertBefore(question, questionDiv.children()[position]);
	//}
    if(position === 0) {
    	$("#allQuestions").prepend(question);
    } else {
    	$("#allQuestions > div:nth-child(" + (position) + ")").after(question);
    }
}
function insertAtIndex(i, newElement, parentElement) {

}

function makeDefaultQuestion(){
	var newQuestion = document.createElement("DIV");
	newQuestion.setAttribute("class", "answerLabel");
	newQuestion.innerHTML= "<div>hello</div>";
	console.log(newQuestion);
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
			"questions" : getQuestions()
	};
	console.log(data);
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