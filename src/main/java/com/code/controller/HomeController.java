package com.code.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {

	// Creating logger object
	Logger logger = LoggerFactory.getLogger(HomeController.class);

	// context root redirects to swagger ui
	@GetMapping("/")
	public String home() {
		logger.debug("Inside home controller");
		logger.debug("Redirecting to : swagger-ui.html");
		return "redirect:swagger-ui.html";
	}

}
