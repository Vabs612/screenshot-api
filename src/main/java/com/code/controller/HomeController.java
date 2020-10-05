package com.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {

//context root redirects to swagger ui
	@GetMapping("/")
	public String home(){
		return "redirect:swagger-ui.html";
	}
	
}
