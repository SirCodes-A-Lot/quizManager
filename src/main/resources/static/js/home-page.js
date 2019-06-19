$(document).ready(function() {
	document.getElementById("addQuizButton").addEventListener("click", addQuiz, false);
});

function addQuiz(){
	$.ajax({
		type : "POST",
		url : "/addQuiz",
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