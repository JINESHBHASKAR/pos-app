package com.pos.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

	@RequestMapping({ "/hello" })
	public String welcomePage() {
		return "Welcome Application";
	}

}