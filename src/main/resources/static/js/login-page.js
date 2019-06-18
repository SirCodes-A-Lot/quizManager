$(document).ready(function() {
	document.getElementById("loginForm").addEventListener("submit", loginFormSubmit, false);
	console.log("in login page js");
});

function loginFormSubmit(e) {
	e.preventDefault();
	ajaxSubmitAnswers(getLoginData());
	console.log(document.getElementById("user").value);
}

function getLoginData() {
	var data = 	{"userType" : document.getElementById("user").value,
			"password" : document.getElementById("password").value};
	var formData = JSON.stringify(data);
	return formData;
}

function ajaxSubmitAnswers(formData) {
	$.ajax({
		type : "POST",
		url : "/login",
		data : formData,
		contentType: "application/json; charset=utf-8",
		success : function(result) {
			if (result.status == "200") {
				console.log("log in success");
				window.location.href = "/home"
			} else {
				console.log("ERROR:  ", "no response");
			}
		},
		error : function(e) {
			console.log("ERROR: ", e);
		}
	});
}

function navigateToHomePage() {
	//On ajax success
}