$(document).ready(function() {
	console.log("edit page js");
	document.getElementById("editQuiz").addEventListener("submit", saveQuizSubmit, false);
});

function saveQuizSubmit(e){
	console.log("here");
	e.preventDefault();
	ajaxSubmitQuiz(getQuizData());
	return false;
}

function getQuizData() {
	var data = {};
	var formData = JSON.stringify(data);
	return formData;
}


function ajaxSubmitQuiz(formData) {
	$.ajax({
		type : "POST",
		url : "/saveQuiz",
		data : formData,
		contentType: "application/json; charset=utf-8",
		success : function(result) {
			if (result.status == "200") {
				console.log(result.data);
			} else {
				console.log("ERROR:  ", "something went wrong");
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}