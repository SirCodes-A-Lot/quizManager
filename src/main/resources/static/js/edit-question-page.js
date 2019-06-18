$(document).ready(function() {
	document.getElementById("saveQuiz").addEventListener("submit", saveQuizSubmit, false);
});

function saveQuizSubmit(e){
	e.preventDefault();
	ajaxSubmitQuiz(getFormData());
}

function getQuizData() {
	var formData = {};
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