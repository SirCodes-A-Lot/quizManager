$(document).ready(function() {
	numberQuestionsAndAnswers();
});

function numberQuestionsAndAnswers(){
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