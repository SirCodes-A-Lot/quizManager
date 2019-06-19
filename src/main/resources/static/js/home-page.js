$(document).ready(function() {
	document.getElementById("addQuizButton").addEventListener("click", addQuiz, false);
	setDeleteQuizHandler();
});

function setDeleteQuizHandler() {
	var deleteQuizButtons = document.getElementsByClassName("deleteQuizButton");
	for (var i = 0; i < deleteQuizButtons.length; i++) {
		deleteQuizButtons[i].addEventListener("click", deleteQuiz, false);
	}
}

function deleteQuiz(e) {
	var quizEntry = e.target.parentElement;
	var quizId = quizEntry.getAttribute("data-quiz-id");
	var data = {"quizId": quizId};
	var formData = JSON.stringify(data);
	quizEntry.parentNode.removeChild(quizEntry);
	$.ajax({
		type : "DELETE",
		url : "/deleteQuiz",
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

function addQuiz(){
	$.ajax({
		type : "POST",
		url : "/addQuiz",
		success : function(result) {
			if (result.status == "200") {
				console.log("success");
				location.reload(true);
			} else {
				console.log("ERROR:  ", "something went wrong");
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}