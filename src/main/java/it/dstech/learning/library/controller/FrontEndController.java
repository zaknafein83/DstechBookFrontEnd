package it.dstech.learning.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontEndController {

	@GetMapping("/exe")
	public String method() {
		return "helloworld";
	}

}