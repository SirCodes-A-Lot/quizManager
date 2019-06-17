package com.quiz.pageControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageControllers {
	
	@GetMapping("/home")
	public String getHomePage() {
		return "home";
	}

}
